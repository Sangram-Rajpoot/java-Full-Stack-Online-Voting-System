package in.learner.votezy.exception;

/**
 * Custom exception thrown when a requested resource is not found in the system.
 * 
 * Example use cases:
 *  - Retrieving a voter, candidate, or election result that does not exist.
 * 
 * Extends RuntimeException, making it an unchecked exception.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Constructor to create the exception with a custom error message.
     *
     * @param message - description of the missing resource
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
