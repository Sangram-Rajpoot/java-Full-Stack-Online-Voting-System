package in.learner.votezy.dto;

import lombok.Data;

@Data	
public class VoterResponseDTO {

    private Long id;
    private String name;
    private String email;
    private boolean hasVoted;

   
}
