package dto;

/**
 *  RouteDTO
 */
public class RouteDTO {

    private String listStation;

    public String getListStation() {
        return listStation;
    }

    public void setListStation(String listStation) {
        this.listStation = listStation;
    }

    public RouteDTO() {
    }

    public RouteDTO(String listStation) {
        this.listStation = listStation;
    }

    @Override
    public String toString() {
        return "RouteDTO{" +
            "listStation=" + listStation +
            '}';
    }
}
