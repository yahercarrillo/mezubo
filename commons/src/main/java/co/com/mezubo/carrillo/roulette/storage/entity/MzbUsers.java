package co.com.mezubo.carrillo.roulette.storage.entity;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * Entidad de usuarios de ruletas ne juego
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public class MzbUsers {
    private String id;
    @NotNull(message = "Code cannot be null")
    private String nickname;
    private String description;
    private BigDecimal credit;
    private Date datecreate;
    private Date dateupdate;
    private boolean enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }

    public Date getDateupdate() {
        return dateupdate;
    }

    public void setDateupdate(Date dateupdate) {
        this.dateupdate = dateupdate;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
