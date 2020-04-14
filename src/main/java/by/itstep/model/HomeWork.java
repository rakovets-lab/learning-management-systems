package by.itstep.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class HomeWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long hwId;

    @NotBlank(message = "Please fill the title")
    @Length(max = 255, message = "Message too long (more than 2kB)")
    private String title;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private String filename;

    private String solution;

    public HomeWork(){
    }

    public HomeWork(String title, String solution, User user) {
        this.author = user;
        this.title = title;
        this.solution = solution;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

}
