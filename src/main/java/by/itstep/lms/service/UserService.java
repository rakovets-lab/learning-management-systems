package by.itstep.lms.service;

import by.itstep.lms.entity.Image;
import by.itstep.lms.model.Role;
import by.itstep.lms.entity.User;
import by.itstep.lms.repository.ImageRepository;
import by.itstep.lms.repository.UserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static by.itstep.lms.common.Messages.ACTIVATE_ACCOUNT_MESSAGE;
import static by.itstep.lms.common.Messages.ACTIVATION_MASSAGE;
import static by.itstep.lms.model.Role.USER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.Collections.singleton;
import static java.util.UUID.randomUUID;
import static org.springframework.util.StringUtils.isEmpty;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;

    public UserService(UserRepository userRepository, MailSender mailSender, PasswordEncoder passwordEncoder, ImageRepository imageRepositiry) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepositiry;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws LockedException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new LockedException("User not found");
        }
        if (!user.isActive()) {
            throw new LockedException("email not activated");
        }
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void saveUser(User user, String username, Map<String, String> form) {
        user.setUsername(username);
        Set<String> roles = stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());

        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email, MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setName(imageFile.getOriginalFilename());
        byte[] byteImg = imageFile.getBytes();
        image.setData(byteImg);
        imageRepository.save(image);
        String userEmail = user.getEmail();
        boolean isEmailChanged = validationMail(email, userEmail);
        if (isEmailChanged) {
            user.setEmail(email);
            user.setActive(false);
            if (!isEmpty(email)) {
                user.setActivationCode(randomUUID().toString());
            }
        }

        if (!isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    private boolean validationMail(String email, String userEmail) {
        return email != null && !email.equals(userEmail) || userEmail != null && !userEmail.equals(email);
    }

    public boolean addUser(User user) {
        if (!validationUser(user)) {
            user.setActive(false);
            user.setRoles(singleton(USER));
            user.setActivationCode(randomUUID().toString());
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(user);
            sendMessage(user);
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public boolean deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId).orElseThrow();
        if (validationUser(user)) {
            userRepository.delete(user);
            return TRUE;
        }else {
            return FALSE;
        }
    }

    private boolean validationUser(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

    private void sendMessage(User user) {
        if (!isEmpty(user.getEmail())) {
            String message = format(ACTIVATE_ACCOUNT_MESSAGE,
                    user.getUsername(),
                    user.getEmail(),
                    user.getActivationCode()
            );
            mailSender.send(user.getEmail(), ACTIVATION_MASSAGE, message);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);

        if (user != null) {
            user.setActive(true);
            userRepository.save(user);
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public List<User> loadUserByRole() throws LockedException {
        List<User> userList = new LinkedList<>();
        Iterable<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getRoles().contains(USER)) {
                userList.add(user);
            }
        }
        return userList;
    }

    public boolean isConfirmEmpty(@RequestParam("password2") String passwordConfirm) {
        return StringUtils.isEmpty(passwordConfirm);
    }

    public boolean isPasswordDifferent(@RequestParam("password2") String passwordConfirm, @AuthenticationPrincipal User user) {
        return user.getPassword() != null && !user.getPassword().equals(passwordConfirm);
    }

    public void passwordConfirmEmpty(@RequestParam("password2") String passwordConfirm, Model model) {
        if (isConfirmEmpty(passwordConfirm)){
            model.addAttribute("password2Error", "Password confirmation can't be empty");
        }
    }
}
