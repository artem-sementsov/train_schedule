package ru.netcracker.train_schedule.domain;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by aleks on 20.04.2016.
 */
@Entity
@Table(name = "answer")
public class Response implements Serializable {

    @Id
    @Column(name = "request_id")
    @Type(type="org.hibernate.type.PostgresUUIDType")
    private UUID id;

    private String answer;

    @Column(name = "created_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime createdDate;

    @Column(name = "status")
    private String status;

    @Column(name = "description_error")
    private String description_error;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription_error() {
        return description_error;
    }

    public void setDescription_error(String description_error) {
        this.description_error = description_error;
    }

    @Override
    public String toString() {
        return "Response{" +
            "id=" + id +
            ", answer='" + answer + '\'' +
            ", createdDate=" + createdDate +
            ", status='" + status + '\'' +
            ", description_error='" + description_error + '\'' +
            '}';
    }
}
