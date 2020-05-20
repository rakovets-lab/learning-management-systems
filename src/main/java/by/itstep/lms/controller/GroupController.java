package by.itstep.lms.controller;

import by.itstep.lms.entity.Group;
import by.itstep.lms.entity.User;
import by.itstep.lms.model.dto.IdDto;
import by.itstep.lms.repository.GroupRepository;
import by.itstep.lms.repository.UserRepository;
import by.itstep.lms.service.UserService;
import by.itstep.lms.service.GroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {

    private final GroupService groupService;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository,
                           UserService userService, GroupService groupService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.groupService = groupService;
    }

    @GetMapping("/groupManagement")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String room(@AuthenticationPrincipal User user, Model model) {
        Iterable<Group> groups = groupRepository.findByGroupLeader(user);
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("groups", groups);

        return "groupManagement";
    }

    @PostMapping("/groupManagement")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public String addGroup(@AuthenticationPrincipal User user, @Valid Group group, Model model) {
        Group group1 = groupRepository.findByGroupName(group.getGroupName());
        if (!group.getGroupName().equals(group1.getGroupName())) {
            group.setGroupLeader(user);
            groupRepository.save(group);
            model.addAttribute("group", group);
        }
        Iterable<Group> groups = groupRepository.findByGroupLeader(user);
        model.addAttribute("groups", groups);

        return "/groupManagement";
    }

    @PostMapping("/group/join")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public String addUserToGroup(IdDto userDto) {
        groupService.addUserToGroup(userDto.getUserId(), userDto.getGroupId());
        return "redirect:/groupManagement/" + userDto.getGroupId();
    }

    @GetMapping("/groupManagement/{groupId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String userGroup(@PathVariable Long groupId, Model model, @RequestParam(required = false) Group group
    ) {
        Optional<Group> currentGroup = groupRepository.findByGroupId(groupId);
        Iterable<Group> groups = groupRepository.findAll();
        List<User> users = userService.loadUserByRole();
        Group usersFromGroup = groupRepository.findByGroupId(groupId).orElseThrow();

        model.addAttribute("usersFromGroup", usersFromGroup);
        model.addAttribute("group", currentGroup.orElse(group));
        assert currentGroup.orElse(null) != null;
        model.addAttribute("studentCount", currentGroup.get().getUsers().size());
        model.addAttribute("groups", groups);
        model.addAttribute("users", users);

        return "userGroups";
    }

}
