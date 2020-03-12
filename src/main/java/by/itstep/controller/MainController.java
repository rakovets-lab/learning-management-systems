package by.itstep.controller;

import by.itstep.model.HW;
import by.itstep.model.User;
import by.itstep.repository.HwRepository;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    private final HwRepository hwRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public MainController(HwRepository hwRepository) {
        this.hwRepository = hwRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<HW> homeWorks = hwRepository.findAll();

        model.addAttribute("homeWorks", homeWorks);
        model.addAttribute("filter", filter);

        return "main";
    }

//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @Valid HW homeWork,
//            BindingResult bindingResult,
//            Model model,
//            @RequestParam("file") MultipartFile file
//    ) throws IOException {
//        homeWork.setAuthor(user);
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
//            model.addAttribute("homeWork", homeWork);
//        } else {
//            if (file != null && !file.getOriginalFilename().isEmpty()) {
//                File uploadDir = new File(uploadPath);
//
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//
//                String uuidFile = UUID.randomUUID().toString();
//                String resultFilename = uuidFile + "." + file.getOriginalFilename();
//                file.transferTo(new File(uploadPath + "/" + resultFilename));
//                homeWork.setFilename(resultFilename);
//            }
//            model.addAttribute("homeWork", null);
//            hwRepository.save(homeWork);
//        }
//        Iterable<HW> homeWorks = hwRepository.findAll();
//        model.addAttribute("homeWorks", homeWorks);
//        return "teacherRoom";
//    }

    @GetMapping("/teacherRoom")
    public String room(Model model) {
        Iterable<HW> homeWorks = hwRepository.findAll();

        model.addAttribute("homeWorks", homeWorks);

        return "teacherRoom";
    }

    @PostMapping("/teacherRoom")
    public String addHW(
            @AuthenticationPrincipal User user,
            @Valid HW homeWork,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        homeWork.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("homeWork", homeWork);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));
                homeWork.setFilename(resultFilename);
            }
            model.addAttribute("homeWork", null);
            hwRepository.save(homeWork);
        }
        Iterable<HW> homeWorks = hwRepository.findAll();
        model.addAttribute("homeWorks", homeWorks);
        return "teacherRoom";
    }
}
