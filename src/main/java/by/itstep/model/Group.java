package by.itstep.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "learning_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter private Long groupId;

    @Getter @Setter private String groupName;

    @JoinColumn(name = "group_leader_name")
    @ManyToOne(fetch = FetchType.LAZY)
    @Getter @Setter private User groupLeader;

    @ManyToMany(mappedBy = "groups")
    @Getter @Setter private Set<User> users = new HashSet<>();

    public Group() {
    }
    public Group(String groupName, User user) {
        this.groupName = groupName;
        this.groupLeader = user;
    }
}
