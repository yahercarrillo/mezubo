package co.com.mezubo.carrillo.roulette.storage.service;

import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import org.springframework.http.ResponseEntity;

/**
 * Servicio general para crear mesas de juego
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public interface MzbRouletteServiceCrud {

    ResponseEntity<Object> findAll();

    ResponseEntity<Object> findById(String id);

    ResponseEntity<Object> findByCode(String code);

    ResponseEntity<Object> insert(MzbRoulette roulette);

}

