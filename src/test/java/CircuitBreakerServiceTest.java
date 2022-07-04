import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CircuitBreakerServiceTest {

    @Test
    void circuitBreakerTest() {
        // Given
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("testName");

        // When I decorate my function and invoke the decorated function
        CheckedFunction0<String> checkedSupplier =
                CircuitBreaker.decorateCheckedSupplier(circuitBreaker, () -> {
                    throw new RuntimeException("BAM!");
                });
        Try<String> result = Try.of(checkedSupplier)
                .recover(throwable -> "Hello Recovery");
        // Then the function should be a success,
        // because the exception could be recovered
        assertTrue(result.isSuccess());

        // and the result must match the result of the recovery function.
        assertEquals(result.get(), "Hello Recovery");
    }
}