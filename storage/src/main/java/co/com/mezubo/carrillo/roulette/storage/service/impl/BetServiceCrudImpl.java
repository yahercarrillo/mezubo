package co.com.mezubo.carrillo.roulette.storage.service.impl;

import co.com.mezubo.carrillo.roulette.storage.dao.MzbRouletteBetsDao;
import co.com.mezubo.carrillo.roulette.storage.dao.MzbRouletteDao;
import co.com.mezubo.carrillo.roulette.storage.dao.MzbUsersDao;
import co.com.mezubo.carrillo.roulette.storage.dto.SpinWinner;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import co.com.mezubo.carrillo.roulette.storage.enumutil.ColorsRoulette;
import co.com.mezubo.carrillo.roulette.storage.service.BetServiceCrud;
import co.com.mezubo.carrillo.roulette.storage.session.SessionRoulette;
import co.com.mezubo.carrillo.roulette.storage.session.SessionTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BetServiceCrudImpl implements BetServiceCrud {

    @Autowired
    MzbRouletteBetsDao betDao;

    @Autowired
    MzbRouletteDao rouletteDao;

    @Autowired
    MzbUsersDao userDao;

    @Autowired
    SessionTokenRepository sessionToken;

    @Value("${security.jwt.token.min-length}")
    private long minSpin;
    @Value("${security.jwt.token.max-length}")
    private long maxSpin;
    @Value("${security.jwt.token.winner-number}")
    private long amountWinnerNumber;
    @Value("${security.jwt.token.winner-color}")
    private String amountWinnerColor;
    @Value("${security.jwt.token.bet-max}")
    private String betMax;

    @Override
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(betDao.findAll());
    }

    @Override
    public void insert(MzbRouletteBets bet) {


        final Iterable<SessionRoulette> sessionTokenOpt = sessionToken.findAll();
        if (size(sessionTokenOpt) > maxSpin)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette exceed the limit to bets!. Only have [" + maxSpin + "] bets");

        MzbRoulette roulette = rouletteDao.findById(bet.getId_roulette());
        if (roulette == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Dont Exist!");
        else if (!roulette.isEnable())
            throw new ResponseStatusException(HttpStatus.DESTINATION_LOCKED, "Roulette Blocked!");

        if (bet.getNumbergame() < minSpin || bet.getNumbergame() > maxSpin)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number Game Not Valid!");

        bet.setColorgame(this.extractColor(bet.getNumbergame()).name());


        MzbRouletteBets item = betDao.findByNumberGame(bet.getNumbergame());
        if (item != null)
            throw new ResponseStatusException(HttpStatus.FOUND, "Record exist!");

        MzbRouletteBets itemUser = betDao.findByUserGame(bet.getUsergame());
        if (itemUser != null)
            throw new ResponseStatusException(HttpStatus.LOCKED, "User have a Bet!");

        BigDecimal maxBet = new BigDecimal(betMax);
        if (bet.getMoney().compareTo(maxBet) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bet exceed the limit!");


        MzbUsers user = userDao.findByNickName(bet.getUsergame());
        bet.setUsergame(user.getId());

        user.setCredit(user.getCredit().subtract(bet.getMoney()));
        userDao.update(user);

        sessionToken.save(new SessionRoulette(bet.getNumbergame(), bet.getUsergame()));
        betDao.insert(bet);
    }

    @Override
    public void update(MzbRouletteBets bet) {
        if (bet.getId() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is null");

        MzbRoulette roulette = rouletteDao.findById(bet.getId_roulette());
        if (roulette == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Dont Exist!");
        else if (!roulette.isEnable())
            throw new ResponseStatusException(HttpStatus.DESTINATION_LOCKED, "Roulette Blocked!");

        if (bet.getNumbergame() < minSpin || bet.getNumbergame() > maxSpin)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Number Game Not Valid!");

        MzbRouletteBets item = betDao.findByNumberGame(bet.getNumbergame());
        if (item != null && !item.getId().equals(bet.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Record exist!");


        MzbRouletteBets betFind = betDao.findById(bet.getId());
        if (betFind == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bet Dont Exist!");

        BigDecimal maxBet = new BigDecimal(betMax);
        if (bet.getMoney().compareTo(maxBet) > 0)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bet exceed the limit!");

        MzbUsers user = userDao.findByNickName(bet.getUsergame());
        bet.setUsergame(user.getId());
        bet.setColorgame(this.extractColor(bet.getNumbergame()).name());
        betDao.update(bet);

        user.setCredit(user.getCredit().subtract(bet.getMoney()));
        userDao.update(user);

    }

    @Override
    public ResponseEntity<Object> openRoulette(String id) {
        if (id == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is null");

        MzbRoulette roulette = rouletteDao.findById(id);
        if (roulette == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Dont Exist!");

        roulette.setEnable(true);
        rouletteDao.update(roulette);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Object> closeRoulette(String id) {
        if (id == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is null");

        MzbRoulette roulette = rouletteDao.findById(id);
        if (roulette == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Dont Exist!");

        roulette.setEnable(false);
        rouletteDao.update(roulette);
        return ResponseEntity.ok(HttpStatus.LOCKED);
    }

    @Override
    public ResponseEntity<SpinWinner> spinRoulette(String id) {

        MzbRoulette roulette = rouletteDao.findById(id);
        if (roulette == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Dont Exist!");

        if (roulette.isEnable())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Roulette Open!");

        double min = (double) minSpin;
        double max = (double) maxSpin;
        double numberwin = getRandomDIntBetweenRange(min, max);
        SpinWinner spinWinner = extractDataWinner(id, numberwin);
        return ResponseEntity.ok(spinWinner);
    }

    private ColorsRoulette extractColor(int number) {
        if (number % 2 == 0)
            return ColorsRoulette.RED;
        else
            return ColorsRoulette.BLACK;
    }

    private SpinWinner extractDataWinner(String idRoulette, double numberwin) {
        SpinWinner spinWinner = new SpinWinner();
        spinWinner.setNumber((int) numberwin);
        spinWinner.setColor(extractColor((int) numberwin).name());
        MzbRouletteBets bet = betDao.findByNumberGame((int) numberwin);
        if (bet != null) {
            spinWinner.setAmount(calculateMoneyWinnerForNumber(bet));
            spinWinner.setPerson(bet.getUsergame());
            spinWinner.setColor(bet.getColorgame());
            MzbRoulette roulette = rouletteDao.findById(bet.getId_roulette());
            spinWinner.setRoulette(roulette.getCode());
            updateMoneyWinners(bet, spinWinner.getAmount());
        } else {
            List<MzbRouletteBets> listwinnersPartial = betDao.findByUsersByColorGame(idRoulette, (extractColor((int) numberwin)).name());
            if (listwinnersPartial != null && !listwinnersPartial.isEmpty()) {
                spinWinner.setListWinnersPartial(listwinnersPartial);
                spinWinner.setPerson(HttpStatus.PARTIAL_CONTENT.toString());
                for (MzbRouletteBets item : listwinnersPartial) {
                    item.setMoney(calculateMoneyWinnerForColor(item));
                    updateMoneyWinners(item, item.getMoney());
                }
            } else {
                spinWinner.setPerson(HttpStatus.NOT_FOUND.toString());
            }
        }
        return spinWinner;
    }

    void updateMoneyWinners(MzbRouletteBets bet, BigDecimal money) {
        MzbUsers user = userDao.findById(bet.getUsergame());
        user.setCredit(user.getCredit().add(money));
        userDao.update(user);
    }

    public static int size(Iterable data) {
        if (data instanceof Collection) {
            return ((Collection<?>) data).size();
        }
        int counter = 0;
        for (Object i : data) {
            counter++;
        }
        return counter;
    }

    private BigDecimal calculateMoneyWinnerForNumber(MzbRouletteBets bet) {
        String porcentWinner = String.valueOf(amountWinnerNumber);
        BigDecimal user = new BigDecimal(porcentWinner);
        return bet.getMoney().multiply(user);
    }

    private BigDecimal calculateMoneyWinnerForColor(MzbRouletteBets bet) {
        String porcentWinner = amountWinnerColor;
        BigDecimal user = new BigDecimal(porcentWinner);
        return bet.getMoney().multiply(user);
    }

    private double getRandomDIntBetweenRange(double min, double max) {
        double x = (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

}
