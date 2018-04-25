package methods;

/**
 * Created by Artem on 4/21/2016.
 */
public class GetAllTicketByUserId {
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "GetAllTicketByUserId{" +
                "Id=" + Id +
                '}';
    }
}
