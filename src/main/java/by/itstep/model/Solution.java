package by.itstep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @Getter @Setter private User author;

    @Getter @Setter private String filename;


    public Solution(){
    }

    public Solution(User user) {
        this.author = user;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}
