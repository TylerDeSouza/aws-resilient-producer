package lambda;


public interface RequestHandler<O> {
    O handleRequest();
}
