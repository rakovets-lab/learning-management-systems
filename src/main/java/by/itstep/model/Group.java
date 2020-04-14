package by.itstep.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "learning_group")
@EqualsAndHashCode(exclude = "users")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String groupName;

    @JoinColumn(name = "group_leader_name")
    @ManyToOne(fetch = FetchType.EAGER)
    private User groupLeader;

    @Fetch(value = FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_group",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn (name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    public Group() {
    }
    public Group(String groupName, User user) {
        this.groupName = groupName;
        this.groupLeader = user;
    }
}
