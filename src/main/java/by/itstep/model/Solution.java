package by.itstep.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long solution_id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private User author;

    private String filename;

    public Solution(){
    }

    public Solution(User user) {
        this.author = user;
    }

    public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
    }
}
