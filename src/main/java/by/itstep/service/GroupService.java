package by.itstep.service;

import by.itstep.model.Group;
import by.itstep.model.User;
import by.itstep.repository.GroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void addUserToGroup(User user, Group group) {
        Group toUpdate = groupRepository.findByGroupId(group.getGroupId())
                .orElseThrow(() -> new RuntimeException(String.format("Group with id %s not found", group.getGroupId())));
        if (!toUpdate.getUsers().contains(user)){
            toUpdate.getUsers().add(user);
        }

        groupRepository.save(group);
    }
}
