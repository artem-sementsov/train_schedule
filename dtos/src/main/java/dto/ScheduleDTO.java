package dto;

import org.joda.time.LocalTime;

/**
 *  ScheduleDTO
 */
public class ScheduleDTO {

    private int weekSchedule;

    private LocalTime time;

    private Long routeId;

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

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public ScheduleDTO() {
    }

    public ScheduleDTO(int weekSchedule, LocalTime time, Long routeId) {
        this.weekSchedule = weekSchedule;
        this.time = time;
        this.routeId = routeId;
    }

    @Override
    public String toString() {
        return "ScheduleDTO{" +
                "weekSchedule=" + weekSchedule +
                ", time=" + time +
                ", routeId=" + routeId +
                '}';
    }
}
