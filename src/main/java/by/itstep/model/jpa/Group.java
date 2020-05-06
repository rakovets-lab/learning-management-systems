package by.itstep.model.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.FetchMode.SELECT;

@Data
@Entity
@Table(name = "learning_group")
@EqualsAndHashCode(exclude = "users")
public class Group {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long groupId;

    private String groupName;

    @JoinColumn(name = "group_leader_name")
    @ManyToOne(fetch = EAGER)
    private User groupLeader;

    @Fetch(value = SELECT)
    @ManyToMany(fetch = EAGER, cascade = REMOVE)
    @JoinTable(name = "user_group",
               joinColumns = {@JoinColumn(name = "group_id")},
               inverseJoinColumns = {@JoinColumn (name = "user_id")})
    private Set<User> users = new HashSet<>();

    public Group() {
    }

    public Group(String groupName, User user) {
        this.groupName = groupName;
        this.groupLeader = user;
    }
}
