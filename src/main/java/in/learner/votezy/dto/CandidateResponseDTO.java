package in.learner.votezy.dto;

import lombok.Data;

@Data
public class CandidateResponseDTO {
    private Long id;
    private String name;
    private String party;
    private int voteCount;
}

