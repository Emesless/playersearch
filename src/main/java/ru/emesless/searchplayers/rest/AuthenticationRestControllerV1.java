package ru.emesless.searchplayers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import ru.emesless.searchplayers.DTO.AuthenticationRequestDTO;
import ru.emesless.searchplayers.DTO.ProfileDTO;
import ru.emesless.searchplayers.DTO.tokenRequestDTO;
import ru.emesless.searchplayers.model.User;
import ru.emesless.searchplayers.repository.UserRepository;
import ru.emesless.searchplayers.security.JwtTokenProvider;
import ru.emesless.searchplayers.service.Users.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import ru.emesless.searchplayers.util.Views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;

    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
            String token = jwtTokenProvider.createToken(request.getEmail(), user.getRole().name());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", request.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    // Получить email
    //Нужно ли ограничить на права пользовотеля
    @GetMapping("/iam")
    @JsonView(Views.UserRole.class)
    public ResponseEntity<ProfileDTO> getLogin(@AuthenticationPrincipal
        org.springframework.security.core.userdetails.User user) {
        ProfileDTO profileDTO = ProfileDTO.fromUser(userService.loadUserByEmail(user.getUsername()));
        return ResponseEntity.ok(profileDTO);
    }

    @GetMapping("/profile/{id}")
    @JsonView(Views.UserInfo.class)
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable("id") Long profileId) {
        User user = userService.getById(profileId);
        ProfileDTO profileDTO = ProfileDTO.fromUser(user);

        return new ResponseEntity<>(profileDTO, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
