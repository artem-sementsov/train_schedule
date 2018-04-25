package dto;

import org.joda.time.DateTime;

import javax.validation.constraints.Size;

/**
 *  TicketDTO
 */
public class TicketDTO {

    @Size(max = 20)
    private String status;

    private int quantity;

    private DateTime createDate;

    private Long flightId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public TicketDTO() {
    }

    public TicketDTO(String status, int quantity, DateTime createDate, Long flightId) {
        this.status = status;
        this.quantity = quantity;
        this.createDate = createDate;
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "TicketDTO{" +
                "status='" + status + '\'' +
                ", quantity=" + quantity +
                ", createDate=" + createDate +
                ", flightId=" + flightId +
                '}';
    }
}
