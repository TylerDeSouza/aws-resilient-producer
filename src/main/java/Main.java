import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        BackendService backendService = new BackendService();
        CircuitBreakerService.circuitBreaker(backendService);
    }
}
