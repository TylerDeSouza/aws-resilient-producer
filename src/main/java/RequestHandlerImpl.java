import lambda.RequestHandler;
import models.Response;
import services.BackendService;
import services.CircuitBreakerService;

public class RequestHandlerImpl implements RequestHandler {

    @Override
    public Response handleRequest() {
        String message = "Hello from Lambda!";
        BackendService backendService = new BackendService();
        CircuitBreakerService.circuitBreaker(backendService);
        return new Response(message, 200);
    }
}