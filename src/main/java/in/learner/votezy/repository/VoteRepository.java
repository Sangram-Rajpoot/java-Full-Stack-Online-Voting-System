package in.learner.votezy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.learner.votezy.entity.Vote;

@Repository // Marks this as a Spring Data JPA repository bean
public interface VoteRepository extends JpaRepository<Vote, Long> {
    
    /*
     * Currently, this repository only extends JpaRepository,
     * which gives you full CRUD operations such as:
     *  - save(Vote vote)
     *  - findById(Long id)
     *  - findAll()
     *  - deleteById(Long id)
     * 
     * You can add custom queries if needed, for example:
     *   List<Vote> findByVoterId(Long voterId);
     *   List<Vote> findByCandidateId(Long candidateId);
     *   Long countByCandidateId(Long candidateId);
     */
}
