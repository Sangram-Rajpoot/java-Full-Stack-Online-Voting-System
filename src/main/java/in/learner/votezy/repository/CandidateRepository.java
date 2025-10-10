package in.learner.votezy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learner.votezy.entity.Candidate;

@Repository // Marks this interface as a Spring Data JPA repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    
    /**
     * Retrieves all candidates ordered by their vote count in descending order.
     * This is useful for ranking candidates by popularity (e.g., leaderboard).
     *
     * @return list of candidates sorted by highest votes first
     */
    List<Candidate> findAllByOrderByVoteCountDesc();
}
