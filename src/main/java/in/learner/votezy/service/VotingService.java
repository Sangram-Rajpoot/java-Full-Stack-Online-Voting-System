package in.learner.votezy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.learner.votezy.entity.Candidate;
import in.learner.votezy.entity.Vote;
import in.learner.votezy.entity.Voter;
import in.learner.votezy.exception.ResourceNotFoundException;
import in.learner.votezy.exception.VoteNotAllowedException;
import in.learner.votezy.repository.CandidateRepository;
import in.learner.votezy.repository.VoteRepository;
import in.learner.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VotingService {
	private VoteRepository voteRepository;
	private CandidateRepository candidateRepository;
	private VoterRepository voterRepository;

	@Autowired
	public VotingService(VoteRepository voteRepository, CandidateRepository candidateRepository,
			VoterRepository voterRepository) {
		this.voteRepository = voteRepository;
		this.candidateRepository = candidateRepository;
		this.voterRepository = voterRepository;
	}

	@Transactional
	public Vote castVote(Long voterId, Long candidateId) {
		if (!voterRepository.existsById(voterId)) {
			throw new ResourceNotFoundException("Voter not found with ID:" + voterId);

		}
		if (!candidateRepository.existsById(candidateId)) {
			throw new ResourceNotFoundException("Candidate Not Found With ID: " + candidateId);
		}
		Voter voter = voterRepository.findById(voterId).get();
		if (voter.isHasVoted()) {
			throw new VoteNotAllowedException("Voter ID: " + voterId + " has already cast vote");

		}
		Candidate candidate = candidateRepository.findById(candidateId).get();
		Vote vote = new Vote();
		vote.setVoter(voter);
		vote.setCandidate(candidate);
	
		// voteRepository.save(vote);

		candidate.setVoteCount(candidate.getVoteCount() + 1);
		candidateRepository.save(candidate);
		voter.setVote(vote);
		voter.setHasVoted(true);
		voterRepository.save(voter);
		return vote;
	}

	public List<Vote> getAllVotes() {
		return voteRepository.findAll();
	}
}
