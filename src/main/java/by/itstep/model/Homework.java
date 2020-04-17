package by.itstep.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@Table(name = "home_work")
public class Homework {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long hwId;

    @NotBlank(message = "Please fill the title")
    @Length(max = 255, message = "Message too long (more than 2kB)")
    private String title;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    private String filename;

    private String solution;

    public Homework(){
    }

    public Homework(String title, String solution, User user) {
        this.author = user;
        this.title = title;
        this.solution = solution;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

}
