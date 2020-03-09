package by.itstep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class HW {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Integer id;
    @Getter @Setter private String title;
    @Getter @Setter private String solution;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Getter @Setter private User author;

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

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getSolution() {
//        return solution;
//    }
//
//    public void setSolution(String solution) {
//        this.solution = solution;
//    }

}
