package co.com.mezubo.carrillo.roulette.storage.service.impl;

import co.com.mezubo.carrillo.roulette.storage.dao.MzbUsersDao;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import co.com.mezubo.carrillo.roulette.storage.service.UsersServiceCrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio general gestionar Usuarios, logica del negocio
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@Service
public class UsersServiceCrudImpl implements UsersServiceCrud {
    private static final Logger logger = LoggerFactory.getLogger(MzbRouletteServiceCrudImpl.class);

    @Autowired
    MzbUsersDao userDao;

    @Override
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(userDao.findAll());
    }

    @Override
    public void insert(MzbUsers user) {
        MzbUsers item = userDao.findByNickName(user.getNickname());
        if (item != null)
            throw new ResponseStatusException(HttpStatus.FOUND, "User exist!");

        userDao.insert(user);
    }

    @Override
    public void update(MzbUsers user) {
        MzbUsers item = userDao.findByNickName(user.getNickname());
        if (item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User dont exist!");

        userDao.update(user);

    }

    @Override
    public ResponseEntity<Object> findByNickName(String nickname) {
        MzbUsers item = userDao.findByNickName(nickname);
        if (item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User dont exist!");
        return ResponseEntity.ok(item);
    }
}
