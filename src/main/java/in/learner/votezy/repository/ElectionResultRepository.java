package in.learner.votezy.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learner.votezy.entity.ElectionResult;

@Repository // Marks this interface as a Spring Data JPA repository
public interface ElectionResultRepository extends JpaRepository<ElectionResult, Long> {

    /**
     * Finds an ElectionResult entity by election name.
     * 
     * Spring Data JPA will automatically generate the query:
     *   SELECT * FROM election_result WHERE election_name = ?;
     *
     * @param electionName - the name of the election
     * @return Optional containing ElectionResult if found, otherwise empty
     */
    Optional<ElectionResult> findByElectionName(String electionName);
}
