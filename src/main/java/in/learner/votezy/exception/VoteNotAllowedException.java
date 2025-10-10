package in.learner.votezy.exception;

/**
 * Custom exception thrown when a voter attempts an action that is not allowed.
 *
 * Example use cases:
 *  - A voter tries to vote more than once.
 *  - A voter attempts to vote in a closed or inactive election.
 *
 * Extends RuntimeException, making it an unchecked exception.
 */
public class VoteNotAllowedException extends RuntimeException {

    /**
     * Constructor to create the exception with a custom error message.
     *
     * @param message - description of why the vote action is not allowed
     */
    public VoteNotAllowedException(String message) {
        super(message);
    }
}
