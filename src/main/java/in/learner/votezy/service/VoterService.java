package in.learner.votezy.service;
import java.util.List;
import org.springframework.stereotype.Service;

import in.learner.votezy.dto.VoterRequestDTO;
import in.learner.votezy.dto.VoterResponseDTO;
import in.learner.votezy.entity.Candidate;
import in.learner.votezy.entity.Voter;
import in.learner.votezy.exception.DuplicateResourceException;
import in.learner.votezy.exception.ResourceNotFoundException;
import in.learner.votezy.repository.CandidateRepository;
import in.learner.votezy.repository.VoterRepository;
import jakarta.transaction.Transactional;

@Service
public class VoterService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    public VoterService(VoterRepository voterRepository, CandidateRepository candidateRepository) {
        this.voterRepository = voterRepository;
        this.candidateRepository = candidateRepository;
    }

    public VoterResponseDTO registerVoter(VoterRequestDTO voterDTO) {
        if (voterRepository.existsByEmail(voterDTO.getEmail())) {
            throw new DuplicateResourceException("Voter with email id: " + voterDTO.getEmail() + " already exists");
        }

        Voter voter = new Voter();
        voter.setName(voterDTO.getName());
        voter.setEmail(voterDTO.getEmail());
        voter.setHasVoted(false);

        Voter savedVoter = voterRepository.save(voter);

        return convertToResponseDTO(savedVoter);
    }

    public List<VoterResponseDTO> getAllVoters() {
        List<Voter> voters = voterRepository.findAll();
        return voters.stream().map(this::convertToResponseDTO).toList();
    }

    public VoterResponseDTO getVoterById(Long id) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voter with id: " + id + " not found"));

        return convertToResponseDTO(voter);
    }

    public VoterResponseDTO updateVoter(Long id, VoterRequestDTO updatedDTO) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voter with id: " + id + " not found"));

        if (updatedDTO.getName() != null) {
            voter.setName(updatedDTO.getName());
        }
        if (updatedDTO.getEmail() != null) {
            voter.setEmail(updatedDTO.getEmail());
        }

        Voter updatedVoter = voterRepository.save(voter);
        return convertToResponseDTO(updatedVoter);
    }

    @Transactional
    public void deleteVoter(Long id) {
        Voter voter = voterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot delete voter with id: " + id + " as it does not exist"));

        if (voter.getVote() != null) {
            Candidate candidate = voter.getVote().getCandidate();
            candidate.setVoteCount(candidate.getVoteCount() - 1);
            candidateRepository.save(candidate);
        }

        voterRepository.delete(voter);
    }

    // helper method to build DTO
    private VoterResponseDTO convertToResponseDTO(Voter voter) {
        VoterResponseDTO dto = new VoterResponseDTO();
        dto.setId(voter.getId());
        dto.setName(voter.getName());
        dto.setEmail(voter.getEmail());
        dto.setHasVoted(voter.isHasVoted());
        return dto;
    }
}
