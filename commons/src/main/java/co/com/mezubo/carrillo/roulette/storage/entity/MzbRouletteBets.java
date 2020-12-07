package co.com.mezubo.carrillo.roulette.storage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Entidad de apuestas
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public class MzbRouletteBets {
    private String id;
    @NotNull(message = "Code cannot be null")
    private String id_roulette;
    @NotNull(message = "Code cannot be null")
    private int numbergame;
    @JsonIgnore
    private String colorgame;
    @NotNull(message = "Code cannot be null")
    private BigDecimal money;
    @NotNull(message = "Code cannot be null")
    private String usergame;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_roulette() {
        return id_roulette;
    }

    public void setId_roulette(String id_roulette) {
        this.id_roulette = id_roulette;
    }

    public String getColorgame() {
        return colorgame;
    }

    public void setColorgame(String colorgame) {
        this.colorgame = colorgame;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getUsergame() {
        return usergame;
    }

    public void setUsergame(String usergame) {
        this.usergame = usergame;
    }

    public int getNumbergame() {
        return numbergame;
    }

    public void setNumbergame(int numbergame) {
        this.numbergame = numbergame;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MzbRouletteBets that = (MzbRouletteBets) o;
        return id.equals(that.id) &&
                id_roulette.equals(that.id_roulette) &&
                colorgame.equals(that.colorgame) &&
                money.equals(that.money) &&
                usergame.equals(that.usergame);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_roulette, colorgame, money, usergame);
    }

    @Override
    public String toString() {
        return "MzbRouletteBets{" +
                "id='" + id + '\'' +
                ", id_roulette='" + id_roulette + '\'' +
                ", colorgame='" + colorgame + '\'' +
                ", money=" + money +
                ", usergame='" + usergame + '\'' +
                '}';
    }
}
