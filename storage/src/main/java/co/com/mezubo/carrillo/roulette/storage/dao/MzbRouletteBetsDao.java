package co.com.mezubo.carrillo.roulette.storage.dao;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;

import java.util.List;

/**
 * Crud de objetos de Apuestas
 *
 * @author Yaher Carrillo
 * @date 07/12/2020
 * @since 07/12/2020
 */
public interface MzbRouletteBetsDao {

    List<MzbRouletteBets> findAll();

    MzbRouletteBets findById(String id);

    List<MzbRouletteBets> findByIdRoulette(String id);

    MzbRouletteBets findByNumberGame(int numberGame);

    MzbRouletteBets findByUserGame(String usergame);

    List<MzbRouletteBets> findByUsersByColorGame(String id_roulette, String colorgame);

    void insert(MzbRouletteBets emp);

    void update(MzbRouletteBets emp);
}
