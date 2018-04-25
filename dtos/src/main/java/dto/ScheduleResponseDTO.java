package dto;

import org.joda.time.DateTime;
import org.joda.time.LocalTime;

/**
 *  ScheduleResponseDTO
 */
public class ScheduleResponseDTO {

    private String type;

    private int numberTrain;

    private int stationStart;

    private int stationEnd;

    private String stationFrom;

    private DateTime datetimeFrom;

    private String stationTo;

    private DateTime datetimeTo;

    private LocalTime travelTime;

    private int quantitySeat;

    private float price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(int numberTrain) {
        this.numberTrain = numberTrain;
    }

    public int getStationStart() {
        return stationStart;
    }

    public void setStationStart(int stationStart) {
        this.stationStart = stationStart;
    }

    public int getStationEnd() {
        return stationEnd;
    }

    public void setStationEnd(int stationEnd) {
        this.stationEnd = stationEnd;
    }

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public DateTime getDatetimeFrom() {
        return datetimeFrom;
    }

    public void setDatetimeFrom(DateTime datetimeFrom) {
        this.datetimeFrom = datetimeFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }

    public DateTime getDatetimeTo() {
        return datetimeTo;
    }

    public void setDatetimeTo(DateTime datetimeTo) {
        this.datetimeTo = datetimeTo;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }

    public int getQuantitySeat() {
        return quantitySeat;
    }

    public void setQuantitySeat(int quantitySeat) {
        this.quantitySeat = quantitySeat;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ScheduleResponseDTO{" +
                "type='" + type + '\'' +
                ", numberTrain=" + numberTrain +
                ", stationStart=" + stationStart +
                ", stationEnd=" + stationEnd +
                ", stationFrom='" + stationFrom + '\'' +
                ", datetimeFrom=" + datetimeFrom +
                ", stationTo='" + stationTo + '\'' +
                ", datetimeTo=" + datetimeTo +
                ", travelTime=" + travelTime +
                ", quantitySeat=" + quantitySeat +
                ", price=" + price +
                '}';
    }
}
