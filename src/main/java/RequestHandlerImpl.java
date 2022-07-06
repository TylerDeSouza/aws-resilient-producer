import lambda.RequestHandler;
import models.Response;
import models.SimpleProducer;
import services.BackendService;
import services.CircuitBreakerService;

public class RequestHandlerImpl implements RequestHandler {

    @Override
    public Response handleRequest() {
        String message = "Hello from Lambda!";
        BackendService backendService = new BackendService();
        CircuitBreakerService.circuitBreaker(backendService);
        SimpleProducer simpleProducer = new SimpleProducer();
        simpleProducer.send();
        return new Response(message, 200);
    }
}