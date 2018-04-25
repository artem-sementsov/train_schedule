package methods;

/**
 * Created by Artem on 4/20/2016.
 */
public enum METHODS {
    GETSCHEDULEBYDATE("getScheduleByDate"),
    GETSTATIONLIST("getStationList");

    private String method;

    METHODS(String method) {
        this.method = method;
    }

    public String method() {
        return method;
    }
}
