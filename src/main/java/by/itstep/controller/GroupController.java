package by.itstep.controller;

import by.itstep.model.Group;
import by.itstep.model.User;
import by.itstep.repository.GroupRepository;
import by.itstep.repository.UserRepository;
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

@Controller
public class GroupController {
    @Autowired
    private UserService userService;

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupController(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
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

    @PostMapping("/addUserToGroup/{group}")
    @PreAuthorize("hasAnyAuthority('TEACHER')")
    public String addUserToGroup(
            @PathVariable User currentUser,
            @PathVariable Group group
    ){
        userService.addUserToGroup(currentUser, group);
        return "redirect:/groupManagement/" + group.getGroupId();
    }
}
