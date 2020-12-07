package co.com.mezubo.carrillo.roulette.storage.dto;

import co.com.mezubo.carrillo.roulette.storage.entity.MzbRouletteBets;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class SpinWinner {
    int number;
    String person;
    String roulette;
    String color;
    BigDecimal amount;
    private String usergame;
    List<MzbRouletteBets> listWinnersPartial;

    public List<MzbRouletteBets> getListWinnersPartial() {
        return listWinnersPartial;
    }

    public void setListWinnersPartial(List<MzbRouletteBets> listWinnersPartial) {
        this.listWinnersPartial = listWinnersPartial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getRoulette() {
        return roulette;
    }

    public void setRoulette(String roulette) {
        this.roulette = roulette;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpinWinner that = (SpinWinner) o;
        return number == that.number &&
                person.equals(that.person) &&
                roulette.equals(that.roulette) &&
                color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, person, roulette, color);
    }

    @Override
    public String toString() {
        return "SpinWinner{" +
                "number=" + number +
                ", person='" + person + '\'' +
                ", roulette='" + roulette + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
