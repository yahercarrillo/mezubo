package co.com.mezubo.carrillo.roulette.storage.service.impl;

import co.com.mezubo.carrillo.roulette.storage.dao.MzbRouletteDao;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.service.MzbRouletteServiceCrud;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Servicio general para crear mesas de juego
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
@Service
public class MzbRouletteServiceCrudImpl implements MzbRouletteServiceCrud {

    private static final Logger logger = LoggerFactory.getLogger(MzbRouletteServiceCrudImpl.class);

    @Autowired
    MzbRouletteDao mzbRouletteDao;


    @Override
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.ok(mzbRouletteDao.findAll());
    }

    @Override
    public ResponseEntity<Object> findById(String id) {
        MzbRoulette item = mzbRouletteDao.findById(id);
        if (item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record dont exist!");
        return ResponseEntity.ok(item);
    }

    @Override
    public ResponseEntity<Object> findByCode(String code) {
        MzbRoulette item = mzbRouletteDao.findByCode(code);
        if (item == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record dont exist!");
        return ResponseEntity.ok(item);
    }

    @Override
    public ResponseEntity<Object> insert(MzbRoulette roulette) {

        MzbRoulette item = mzbRouletteDao.findByCode(roulette.getCode());
        if (item != null)
            throw new ResponseStatusException(HttpStatus.FOUND, "Record exist!");

        mzbRouletteDao.insert(roulette);
        return this.findByCode(roulette.getCode());
    }
}
