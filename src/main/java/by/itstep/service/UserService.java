package by.itstep.service;

import by.itstep.model.Role;
import by.itstep.model.jpa.User;
import by.itstep.repository.UserRepository;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static by.itstep.common.Messages.ACTIVATE_ACCOUNT_MESSAGE;
import static by.itstep.common.Messages.ACTIVATION_MASSAGE;
import static by.itstep.model.Role.USER;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.UUID.randomUUID;
import static org.springframework.util.StringUtils.isEmpty;
import static java.util.Collections.singleton;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private MailSender mailSender;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws LockedException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new LockedException("User not found");
        }
        if (!user.isActive()){
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
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = validationMail(email, userEmail);

        if (isEmailChanged) {
            user.setEmail(email);
            if (!isEmpty(email)){
                user.setActivationCode(randomUUID().toString());
            }
        }

        if (!isEmpty(password)){
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    private boolean validationMail(String email, String userEmail) {
        //проверь потом (отрабатывает корректно без скобок или нет)!
        return email != null && !email.equals(userEmail) || userEmail != null && !userEmail.equals(email);
    }

    public boolean addUser(User user) {
        if (validationUser(user)) {
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

    private boolean validationUser(User user) {
        return userRepository.findByUsername(user.getUsername()) != null;
    }

    private void sendMessage(User user) {
        if (!isEmpty(user.getEmail())){
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
            if(user.getRoles().contains(USER)){
                userList.add(user);
            }
        }
        return userList;
    }
}
