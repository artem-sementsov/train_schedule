package dto;

/**
 * TrainDTO
 */
public class TrainDTO {

    private int number;

    private int numberOfSeats;

    public TrainDTO() {
    }

    public TrainDTO(int number, int numberOfSeats) {
        this.number = number;
        this.numberOfSeats = numberOfSeats;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "TrainDTO{" +
            "number=" + number +
            ", numberOfSeats=" + numberOfSeats +
            '}';
    }
}
