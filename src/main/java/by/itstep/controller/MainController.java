package by.itstep.controller;

import by.itstep.model.HW;
import by.itstep.model.User;
import by.itstep.repository.HwRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;


@Controller
public class MainController {
    @Autowired
    private HwRepository hwRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
            @RequestParam String solution, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        HW hw = new HW(title, solution, user);

        if (file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            hw.setFilename(resultFilename);
        }

        hwRepository.save(hw);

        Iterable<HW> hws = hwRepository.findAll();

        model.put("home work", hws);

        return "main";
    }
}
