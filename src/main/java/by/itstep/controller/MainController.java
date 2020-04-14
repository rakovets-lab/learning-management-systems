package by.itstep.controller;

import by.itstep.model.HomeWork;
import by.itstep.model.Solution;
import by.itstep.model.User;
import by.itstep.repository.HomeWorkRepository;
import by.itstep.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
import java.util.Objects;
import java.util.UUID;

@Controller
public class MainController {
    private final HomeWorkRepository homeWorkRepository;
    private final SolutionRepository solutionRepository;


    @Value("${homeWork.path}")
    private String homeWorkPath;
    @Value("${solution.path}")
    private String solutionPath;

    public MainController(HomeWorkRepository homeWorkRepository, SolutionRepository solutionRepository) {
        this.homeWorkRepository = homeWorkRepository;
        this.solutionRepository = solutionRepository;
    }

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Iterable<HomeWork> homeWorks = homeWorkRepository.findAll();
        Iterable<Solution> solutions = solutionRepository.findAll();

        model.addAttribute("homeWorks", homeWorks);
        model.addAttribute("solutions", solutions);

        return "main";
    }

    @GetMapping("/teacherRoom")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String room(Model model) {
        Iterable<HomeWork> homeWorks = homeWorkRepository.findAll();
        Iterable<Solution> solutions = solutionRepository.findAll();

        model.addAttribute("homeWorks", homeWorks);
        model.addAttribute("solutions", solutions);

        return "teacherRoom";
    }

    @PostMapping("/teacherRoom")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String addHW(
            @AuthenticationPrincipal User user,
            @Valid HomeWork homeWork,
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
            if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
                homeWork.setFilename(createFile(file, homeWorkPath));
            }
            model.addAttribute("homeWork", null);
            homeWorkRepository.save(homeWork);
        }
        Iterable<HomeWork> homeWorks = homeWorkRepository.findAll();
        model.addAttribute("homeWorks", homeWorks);
        return "teacherRoom";
    }

    @PostMapping("/main")
    @PreAuthorize("hasAuthority('USER')")
    public String addSolution(
            @AuthenticationPrincipal User user,
            @Valid Solution solution,
            Model model,
            @RequestParam(value = "file", required = false) MultipartFile file
    ) throws IOException {
        solution.setAuthor(user);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            solution.setFilename(MainController.createFile(file, solutionPath));
        }
        solutionRepository.save(solution);

        Iterable<Solution> solutions = solutionRepository.findAll();
        model.addAttribute("solutions", solutions);
        return "main";
    }


    static String createFile(@RequestParam("file") MultipartFile file, String solutionPath) throws IOException {
        File uploadDir = new File(solutionPath);

        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFilename = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(solutionPath + "/" + resultFilename));
        return resultFilename;
    }
}
