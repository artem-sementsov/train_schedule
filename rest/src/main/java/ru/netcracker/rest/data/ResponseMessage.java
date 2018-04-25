package ru.netcracker.rest.data;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.Column;
import java.util.UUID;

/**
 * Created by Artem on 4/20/2016.
 */
public class ResponseMessage {
    @Column(name = "request_id")
    private UUID requestId;

    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name = "created_date")
    private LocalDate createdDate;

    private String answer;

    public ResponseMessage(){
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public UUID getRequestId(){
        return requestId;
    }
}
