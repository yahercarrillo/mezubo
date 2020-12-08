package co.com.mezubo.carrillo.roulette.storage.session;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;

import java.util.Objects;

/**
 * Modelo redis para el almacenamiento de los numeros en ruleta.
 *
 * @author Yaher Carrillo
 * @date 07/12/2020
 * @date 07/12/2020
 */
@RedisHash(
        value = "SessionRoulette",
        timeToLive = 300 //2.5 minutes
)
public class SessionRoulette {
    @Id
    private int numbergame;
    private String usergame;

    public SessionRoulette(int numbergame, String usergame) {
        this.numbergame = numbergame;
        this.usergame = usergame;
    }

    public int getNumbergame() {
        return numbergame;
    }

    public void setNumbergame(int numbergame) {
        this.numbergame = numbergame;
    }

    public String getUsergame() {
        return usergame;
    }

    public void setUsergame(String usergame) {
        this.usergame = usergame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessionRoulette that = (SessionRoulette) o;
        return numbergame == that.numbergame &&
                Objects.equals(usergame, that.usergame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbergame, usergame);
    }

    @Override
    public String toString() {
        return "sessionRoulette{" +
                "numbergame='" + numbergame + '\'' +
                ", usergame='" + usergame + '\'' +
                '}';
    }
}
