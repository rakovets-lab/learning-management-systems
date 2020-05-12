package by.itstep.lms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Solution {
    @Id
    @GeneratedValue(strategy = IDENTITY)
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
