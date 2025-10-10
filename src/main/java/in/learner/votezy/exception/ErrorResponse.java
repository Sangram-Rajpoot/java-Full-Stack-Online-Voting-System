package in.learner.votezy.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Standard structure for sending error responses in APIs.
 * 
 * Typically used with a Global Exception Handler (@ControllerAdvice)
 * to return meaningful error messages and HTTP status codes.
 */
@Data
@AllArgsConstructor // Generates constructor with all fields
@NoArgsConstructor  // Generates default constructor
public class ErrorResponse {

    /**
     * HTTP status code of the error (e.g., 404, 409, 500)
     */
    private int statusCode;

    /**
     * Detailed error message explaining the cause of the error
     */
    private String message;
}
