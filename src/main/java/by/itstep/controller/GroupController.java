package by.itstep.controller;

import by.itstep.model.Group;
import by.itstep.model.User;
import by.itstep.repository.GroupRepository;
import by.itstep.repository.UserRepository;
import by.itstep.service.GroupService;
import by.itstep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/groupManagement")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String room(Model model) {
        Iterable<Group> groups = groupRepository.findAll();
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("users", users);
        model.addAttribute("groups", groups);

        return "groupManagement";
    }

    @PostMapping("/groupManagement")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public String addGroup(
            @AuthenticationPrincipal User user,
            @Valid Group group,
            Model model
    ){
        group.setGroupLeader(user);

        groupRepository.save(group);

        model.addAttribute("group", group);

        Iterable<Group> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);

        return "/groupManagement";
    }

    @GetMapping("/joinInto/{group}/{user}")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public String addUserToGroup(
            @PathVariable Group group,
            @PathVariable User user
    ){
        groupService.addUserToGroup(user, group);
        return "redirect:/groupManagement/" + group.getGroupId();
    }

    @GetMapping("/groupManagement/{groupId}/list")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String groupList(
            Model model,
            @PathVariable Long groupId,
            @PathVariable(required = false) Group group
    ) {
        Group usersFromGroup = groupRepository.findByGroupId(groupId).orElseThrow();

        model.addAttribute("usersFromGroup", usersFromGroup);

        return "groupList";
    }

    @GetMapping("/groupManagement/{groupId}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String userGroup(
            @PathVariable Long groupId,
            Model model,
            @PathVariable(required = false) Group group
    ) {
        Optional<Group> currentGroup = groupRepository.findByGroupId(groupId);
        Iterable<Group> groups = groupRepository.findAll();
        List<User> users = userService.loadUserByRole();

        model.addAttribute("group", currentGroup.orElse(group));
        assert currentGroup.orElse(null) != null;
        model.addAttribute("subscribersCount", currentGroup.get().getUsers().size());
        model.addAttribute("groups", groups);
        model.addAttribute("users", users);

        return "userGroups";
    }
}
