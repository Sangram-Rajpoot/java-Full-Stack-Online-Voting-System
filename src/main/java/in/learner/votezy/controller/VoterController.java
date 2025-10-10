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

import in.learner.votezy.dto.VoterRequestDTO;
import in.learner.votezy.dto.VoterResponseDTO;
import in.learner.votezy.service.VoterService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin(origins = "*")
public class VoterController {

    private final VoterService voterService;

    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/register")
    public ResponseEntity<VoterResponseDTO> registerVoter(@RequestBody @Valid VoterRequestDTO voterDTO) {
        VoterResponseDTO savedVoter = voterService.registerVoter(voterDTO);
        return new ResponseEntity<>(savedVoter, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoterResponseDTO> getVoterById(@PathVariable Long id) {
        VoterResponseDTO voter = voterService.getVoterById(id);
        return new ResponseEntity<>(voter, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VoterResponseDTO>> getAllVoters() {
        List<VoterResponseDTO> voters = voterService.getAllVoters();
        return new ResponseEntity<>(voters, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VoterResponseDTO> updateVoter(@PathVariable Long id, @RequestBody VoterRequestDTO voterDTO) {
        VoterResponseDTO updated = voterService.updateVoter(id, voterDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long id) {
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter with id: " + id + " deleted", HttpStatus.OK);
    }
}
