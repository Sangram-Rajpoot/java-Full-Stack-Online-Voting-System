package in.learner.votezy.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
		@NotNull(message="Election Name  Required")
		private String ElectionName;
}
