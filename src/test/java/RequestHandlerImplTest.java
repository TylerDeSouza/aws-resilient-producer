import models.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestHandlerImplTest {

    @Test
    void handleRequestTest() {
        RequestHandlerImpl requestHandler = new RequestHandlerImpl();
        Response response = requestHandler.handleRequest();
        assertEquals(response.getBody(), "Hello from Lambda!");
    }
}