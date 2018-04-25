package methods;

/**
 * Created by aleks on 16.04.2016.
 */
public class BuyTicket {

    private Long flightId;

    private Long userId;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BuyTicket{" +
            "flightId=" + flightId +
            ", userId=" + userId +
            '}';
    }
}
