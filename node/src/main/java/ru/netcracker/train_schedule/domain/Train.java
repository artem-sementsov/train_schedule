package ru.netcracker.train_schedule.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  A train
 */
@Entity
@Table(name = "train")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Train implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "train_id")
    private Long id;

    @Column(name = "number", unique = true)
    private int number;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Train train = (Train) o;

        if (number != train.number) return false;
        return id.equals(train.id);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number;
        result = 31 * result + numberOfSeats;
        return result;
    }

    @Override
    public String toString() {
        return "Train{" +
            "id=" + id +
            ", number=" + number +
            ", numberOfSeats=" + numberOfSeats +
            '}';
    }
}
