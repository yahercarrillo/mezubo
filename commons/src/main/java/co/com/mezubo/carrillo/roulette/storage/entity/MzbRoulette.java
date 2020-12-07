package co.com.mezubo.carrillo.roulette.storage.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * Entidad de operaciones de ruletas ne juego
 *
 * @author Yaher Carrillo
 * @date 06/12/2020
 * @since 06/12/2020
 */
public class MzbRoulette {
    private String id;
    @NotNull(message = "Code cannot be null")
    private String code;
    private String description;
    private Date datecreate;
    private Date dateupdate;
    private boolean enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MzbRoulette that = (MzbRoulette) o;
        return enable == that.enable &&
                id.equals(that.id) &&
                code.equals(that.code) &&
                description.equals(that.description) &&
                datecreate.equals(that.datecreate) &&
                dateupdate.equals(that.dateupdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, datecreate, dateupdate, enable);
    }

    @Override
    public String toString() {
        return "MzbRoulette{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", datecreate=" + datecreate +
                ", dateupdate=" + dateupdate +
                ", enable=" + enable +
                '}';
    }
}
