package by.itstep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class HW {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;
    @Getter @Setter private String title;
    @Getter @Setter private String solution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Getter @Setter private User author;

    @Getter @Setter private String filename;

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
