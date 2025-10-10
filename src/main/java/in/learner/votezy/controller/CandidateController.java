package in.learner.votezy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.learner.votezy.dto.CandidateRequestDTO;
import in.learner.votezy.dto.CandidateResponseDTO;
import in.learner.votezy.service.CandidateService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    /**
     * Endpoint to add a new candidate.
     * URL: POST /api/candidates/add
     */
    @PostMapping("/add")
    public ResponseEntity<CandidateResponseDTO> addCandidate(@RequestBody @Valid CandidateRequestDTO candidateDTO) {
        CandidateResponseDTO savedCandidate = candidateService.addCandidate(candidateDTO);
        return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get all candidates.
     * URL: GET /api/candidates
     */
    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {
        List<CandidateResponseDTO> candidateList = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    /**
     * Endpoint to get a specific candidate by ID.
     * URL: GET /api/candidates/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(@PathVariable Long id) {
        CandidateResponseDTO candidate = candidateService.getCandidateById(id);
        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }

    /**
     * Endpoint to update a candidate by ID.
     * URL: PUT /api/candidates/update/{id}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<CandidateResponseDTO> updateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateRequestDTO candidateDTO
    ) {
        CandidateResponseDTO updatedCandidate = candidateService.updateCandidate(id, candidateDTO);
        return new ResponseEntity<>(updatedCandidate, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a candidate by ID.
     * URL: DELETE /api/candidates/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with ID: " + id + " deleted successfully", HttpStatus.OK);
    }
}
