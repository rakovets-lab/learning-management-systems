package by.itstep.controller;

import by.itstep.model.jpa.User;
import by.itstep.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "");
        return ("/registration");
    }

    @PostMapping("/registration")
    public String addUser(
            @RequestParam("password2") String passwordConfirm,
            @Valid User user,
            BindingResult bindingResult,
            Model model
    ) {
        boolean isPasswordDifferent = user.getPassword() != null &&
                !user.getPassword().equals(passwordConfirm);
        if (isPasswordDifferent) {
            model.addAttribute("passwordError", "Password are different");
        }

        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        if (isConfirmEmpty){
            model.addAttribute("password2Error", "Password confirmation can't be empty");
        }

        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)){
            bindingResult.addError(new FieldError("user", "password", "Password are different"));
            return "registration";
        }

        if (isConfirmEmpty || bindingResult.hasErrors() || isPasswordDifferent) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code){
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("messageType", "success");
            model.addAttribute("message", "User successfully activated");
        } else {
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Activation code is not found");
        }

        return "login";
    }
}
