package by.itstep.controller;

import by.itstep.model.HW;
import by.itstep.model.User;
import by.itstep.repository.HwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
public class MainController {
    @Autowired
    private HwRepository hwRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<HW> hw;

        if (filter != null && !filter.isEmpty()) {
            hw = hwRepository.findByTitle(filter);
        } else {
            hw = hwRepository.findAll();
        }

        model.addAttribute("hw", hw);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String solution, Map<String, Object> model) {
        HW hw = new HW(title, solution, user);

        hwRepository.save(hw);

        Iterable<HW> hws = hwRepository.findAll();

        model.put("home work", hws);

        return "main";
    }
}
