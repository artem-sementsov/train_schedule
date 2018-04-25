package methods;

/**
 * Created by Artem on 4/20/2016.
 */
public class GetTrainByID {

    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    @Override
    public String toString() {
        return "GetTrainByID{" +
                "Id=" + Id +
                '}';
    }
}
