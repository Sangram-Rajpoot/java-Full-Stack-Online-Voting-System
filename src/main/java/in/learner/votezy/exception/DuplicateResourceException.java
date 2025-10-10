package in.learner.votezy.exception;

/**
 * Custom exception for handling duplicate resource scenarios.
 * 
 * Example use cases:
 *  - Trying to register a voter with an already existing email.
 *  - Adding a candidate with a name that already exists (if uniqueness is required).
 *
 * Extends RuntimeException so it is unchecked (no need to declare in method signatures).
 */
public class DuplicateResourceException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message - detailed error message describing the duplicate resource issue
     */
    public DuplicateResourceException(String message) {
        super(message);
    }
}
