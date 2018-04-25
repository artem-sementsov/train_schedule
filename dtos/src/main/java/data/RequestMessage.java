package data;

import java.util.UUID;

/**
 * Created by aleks on 15.04.2016.
 */

public class RequestMessage {

    private String method;

    private UUID requestId;

    private String requestData;

    public RequestMessage() {
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
            "method='" + method + '\'' +
            ", requestId=" + requestId +
            ", requestData='" + requestData + '\'' +
            '}';
    }
}
