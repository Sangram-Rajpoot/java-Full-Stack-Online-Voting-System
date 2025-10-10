package in.learner.votezy.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity               // Marks this class as a JPA entity (mapped to a database table)
@Data                 // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id;

    @NotBlank(message = "Name is required")  // Validates that the name field is not blank
    private String name;

    @NotBlank(message = "Party is required") // Validates that the party field is not blank
    private String party;

    private int voteCount = 0; // Stores the number of votes received by the candidate (optional, can be derived)

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL) // One-to-many relationship with Vote entity
    @JsonIgnore // Prevents infinite recursion during JSON serialization (important for bidirectional relationships)
    private List<Vote> vote; // List of votes associated with this candidate
}
