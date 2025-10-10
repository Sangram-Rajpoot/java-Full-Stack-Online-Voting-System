package in.learner.votezy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.learner.votezy.dto.CandidateRequestDTO;
import in.learner.votezy.dto.CandidateResponseDTO;
import in.learner.votezy.entity.Candidate;
import in.learner.votezy.entity.Vote;
import in.learner.votezy.exception.ResourceNotFoundException;
import in.learner.votezy.repository.CandidateRepository;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateResponseDTO addCandidate(CandidateRequestDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setParty(candidateDTO.getParty());
        candidate.setVoteCount(0);

        Candidate saved = candidateRepository.save(candidate);
        return convertToResponseDTO(saved);
    }

    public List<CandidateResponseDTO> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public CandidateResponseDTO getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate with id: " + id + " not found"));

        return convertToResponseDTO(candidate);
    }

    public CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO updatedDTO) {
        Candidate candidate = getCandidateByEntity(id);

        if (updatedDTO.getName() != null) {
            candidate.setName(updatedDTO.getName());
        }
        if (updatedDTO.getParty() != null) {
            candidate.setParty(updatedDTO.getParty());
        }

        Candidate updated = candidateRepository.save(candidate);
        return convertToResponseDTO(updated);
    }

    public void deleteCandidate(Long id) {
        Candidate candidate = getCandidateByEntity(id);

        List<Vote> votes = candidate.getVote();
        for (Vote v : votes) {
            v.setCandidate(null);
        }

        candidate.getVote().clear();
        candidateRepository.delete(candidate);
    }

    // ------------------- Helper Methods -------------------

    private Candidate getCandidateByEntity(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate with id: " + id + " not found"));
    }

    private CandidateResponseDTO convertToResponseDTO(Candidate candidate) {
        CandidateResponseDTO dto = new CandidateResponseDTO();
        dto.setId(candidate.getId());
        dto.setName(candidate.getName());
        dto.setParty(candidate.getParty());
        dto.setVoteCount(candidate.getVoteCount());
        return dto;
    }
}
