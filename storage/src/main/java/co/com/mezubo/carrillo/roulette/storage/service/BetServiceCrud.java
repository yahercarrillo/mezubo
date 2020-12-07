package co.com.mezubo.carrillo.roulette.storage.service;

import co.com.mezubo.carrillo.roulette.storage.dto.SpinWinner;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import org.springframework.http.ResponseEntity;

/**
 * Servicio general para crear mesas de juego
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public interface BetServiceCrud {

    ResponseEntity<Object> findAll();

    void insert(MzbRouletteBets roulette);

    void update(MzbRouletteBets roulette);

    ResponseEntity<Object> openRoulette(String id);

    ResponseEntity<Object> closeRoulette(String id);

    ResponseEntity<SpinWinner> spinRoulette(String id);

}

