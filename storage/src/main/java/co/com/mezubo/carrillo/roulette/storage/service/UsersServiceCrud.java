package co.com.mezubo.carrillo.roulette.storage.service;

import co.com.mezubo.carrillo.roulette.storage.dto.SpinWinner;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;
import org.springframework.http.ResponseEntity;

/**
 * Servicio general para gestionar usuarios
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public interface UsersServiceCrud {

    ResponseEntity<Object> findAll();

    void insert(MzbUsers user);

    void update(MzbUsers user);

    ResponseEntity<Object> findByNickName(String nickname);


}

