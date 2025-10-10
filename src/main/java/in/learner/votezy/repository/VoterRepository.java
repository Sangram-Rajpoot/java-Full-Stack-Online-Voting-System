package in.learner.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learner.votezy.entity.Voter;

@Repository // Marks this as a Spring Data JPA repository bean
public interface VoterRepository extends JpaRepository<Voter, Long> {

    /**
     * Checks if a voter exists with the given email.
     * 
     * Spring Data JPA automatically generates the query:
     *   SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END 
     *   FROM voter v 
     *   WHERE v.email = ?;
     *
     * @param email - email to check
     * @return true if a voter with the given email exists, otherwise false
     */
    boolean existsByEmail(String email);

    /*
     * ✅ Extension ideas for future:
     * - Find a voter by email:
     *   Optional<Voter> findByEmail(String email);
     *
     * - Search voters by name (useful for admin dashboards):
     *   List<Voter> findByNameContainingIgnoreCase(String name);
     *
     * - Count voters by domain (e.g., "gmail.com"):
     *   Long countByEmailEndingWith(String domain);
     */
}
