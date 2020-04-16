package by.itstep.service;

import by.itstep.model.Group;
import by.itstep.model.User;
import by.itstep.repository.GroupRepository;
import by.itstep.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public void addUserToGroup(Long userId, Long groupId) {
        Group group = groupRepository.findByGroupId(groupId)
                .orElseThrow(() -> new RuntimeException(String.format("Group with id %s has not found", groupId)));
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(String.format("User with id %s has not found", userId)));
        if (!group.getUsers().contains(user)){
            group.getUsers().add(user);
        }

        groupRepository.save(group);
    }
}
