package by.itstep.service;

import by.itstep.model.jpa.Group;
import by.itstep.model.jpa.User;
import by.itstep.repository.GroupRepository;
import by.itstep.repository.UserRepository;
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
        // подожди, а что ты тут с ошибкой длеаешь, ты же её ни как не обрабатываешь?!
        // проверь этот кейс!! потестируй
        if (!group.getUsers().contains(user)){
            group.getUsers().add(user);
        }

        groupRepository.save(group);
    }
}
