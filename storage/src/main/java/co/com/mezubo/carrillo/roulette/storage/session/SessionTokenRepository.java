package co.com.mezubo.carrillo.roulette.storage.session;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Modelo redis para el almacenamiento de los numeros en ruleta.
 *
 * @author Yaher Carrillo
 * @date 07/12/2020
 * @date 07/12/2020
 */
@Repository
public interface SessionTokenRepository extends CrudRepository<SessionRoulette, String> {
}
