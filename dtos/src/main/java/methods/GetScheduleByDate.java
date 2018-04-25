package methods;

/**
 * Created by aleks on 15.04.2016.
 */
public class GetScheduleByDate {

    private Long stationFromId;

    private Long stationToId;

    private Long date;

    public Long getStationFromId() {
        return stationFromId;
    }

    public void setStationFromId(Long stationFromId) {
        this.stationFromId = stationFromId;
    }

    public Long getStationToId() {
        return stationToId;
    }

    public void setStationToId(Long stationToId) {
        this.stationToId = stationToId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GetScheduleByDate{" +
            "stationFromId=" + stationFromId +
            ", stationToId=" + stationToId +
            ", date=" + date +
            '}';
    }
}
