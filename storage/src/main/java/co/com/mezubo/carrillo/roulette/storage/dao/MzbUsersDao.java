package co.com.mezubo.carrillo.roulette.storage.dao;


import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;
import co.com.mezubo.carrillo.roulette.storage.entity.MzbUsers;

import java.util.List;

/**
 * Crud de objetos de Usuarios
 *
 * @author Yaher Carrillo
 * @date 07/12/2020
 * @since 07/12/2020
 */
public interface MzbUsersDao {

    List<MzbUsers> findAll();

    MzbUsers findById(String id);

    MzbUsers findByNickName(String nickname);

    void insert(MzbUsers emp);

    void update(MzbUsers emp);
}
