package by.itstep.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class HW {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @NotBlank(message = "Please fill the title")
    @Length(max = 255, message = "Message too long (more than 2kB)")
    @Getter @Setter private String title;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter private User author;

    @Getter @Setter private String filename;

    @Getter @Setter private String solution;

    public HW(){
    }

    public HW(String title, String solution, User user) {
        this.author = user;
        this.title = title;
        this.solution = solution;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }

}
