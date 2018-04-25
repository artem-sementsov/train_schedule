package methods;

/**
 * Created by aleks on 16.04.2016.
 */
public class GetTrainByFlight {

    private Long flightId;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "GetTrainByFlight{" +
            "flightId=" + flightId +
            '}';
    }
}
