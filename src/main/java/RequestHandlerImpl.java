import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import models.Response;
import services.SimpleProducerImpl;

public class RequestHandlerImpl implements RequestHandler<String, Response> {
    private final SimpleProducerImpl simpleProducerImpl;

    public RequestHandlerImpl(SimpleProducerImpl simpleProducerImpl) {
        this.simpleProducerImpl = simpleProducerImpl;
    }


    @Override
    public Response handleRequest(String s, Context context) {
        simpleProducerImpl.send(s, s);
        return new Response("Request completed", 200);
    }
}