package by.itstep.lms.service;

import by.itstep.lms.entity.Group;
import by.itstep.lms.entity.User;
import by.itstep.lms.repository.GroupRepository;
import by.itstep.lms.repository.UserRepository;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

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
                .orElseThrow(() -> new RuntimeException(format("Group with id %s has not found", groupId)));
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(format("User with id %s has not found", userId)));
        if (!group.getUsers().contains(user)){
            group.getUsers().add(user);
        }

        groupRepository.save(group);
    }
}
