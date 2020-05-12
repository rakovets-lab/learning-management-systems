package by.itstep.lms.controller;

import by.itstep.lms.entity.Image;
import by.itstep.lms.entity.User;
import by.itstep.lms.model.Role;
import by.itstep.lms.model.dto.IdDto;
import by.itstep.lms.service.UserService;
import by.itstep.lms.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class UserController {

    @Value("${avatar.path}")
    private String avatarPath;

    private final UserService userService;
    private final ImageRepository imageRepository;

    public UserController(UserService userService, ImageRepository imageRepository) {
        this.userService = userService;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userList(Model model){
        model.addAttribute("users", userService.findAll());
        return "userList";
    }
    @GetMapping("{user}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userSave(@RequestParam String username, @RequestParam Map<String, String> form, @RequestParam("userId") User user) {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @PostMapping("delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String userDelete(IdDto idDto) {
        userService.deleteUser(idDto.getUserId());
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("message", "");
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User currentUser,
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam String password,
            @RequestParam String email
    ) throws IOException {
        userService.passwordConfirmEmpty(passwordConfirm, model);

        if (userService.isPasswordDifferent(passwordConfirm, user)){
            bindingResult.addError(new FieldError("user", "password", "Password are different"));
            model.addAttribute("passwordError", "Password are different");
            return "profile";
        }

        if (userService.isConfirmEmpty(passwordConfirm) || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "profile";
        }

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            MainController.createFile(file, avatarPath);
        }

        userService.updateProfile(currentUser, password, email);
        return "redirect:/user/profile";
    }

    @GetMapping("image")
    public String showImage(String name, Model model) {
        Image image = imageRepository.findFirstByName(name);
        model.addAttribute("image", image);
        return "image";
    }

}
