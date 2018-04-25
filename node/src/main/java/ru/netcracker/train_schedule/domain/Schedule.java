package ru.netcracker.train_schedule.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  Schedule
 */

@Entity
@Table(name = "schedule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Schedule implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "week_schedule")
    private int weekSchedule;

    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "id_route", nullable = false)
    private Route route;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(int weekSchedule) {
        this.weekSchedule = weekSchedule;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (weekSchedule != schedule.weekSchedule) return false;
        if (id != null ? !id.equals(schedule.id) : schedule.id != null) return false;
        if (time != null ? !time.equals(schedule.time) : schedule.time != null) return false;
        return route != null ? route.equals(schedule.route) : schedule.route == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + weekSchedule;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Schedule{" +
            "id=" + id +
            ", weekSchedule=" + weekSchedule +
            ", time=" + time +
            ", route=" + route +
            '}';
    }
}
