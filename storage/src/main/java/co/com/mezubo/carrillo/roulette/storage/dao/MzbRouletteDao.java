package co.com.mezubo.carrillo.roulette.storage.dao;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRoulette;

import java.util.List;

/**
 * Crud de objetos Roulette
 */
public interface MzbRouletteDao {

    List<MzbRoulette> findAll();

    MzbRoulette findById(String id);

    MzbRoulette findByCode(String code);

    void insert(MzbRoulette emp);

    void update(MzbRoulette emp);
}
