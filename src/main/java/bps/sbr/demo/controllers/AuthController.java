package bps.sbr.demo.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import bps.sbr.demo.models.ERole;
import bps.sbr.demo.models.Role;
import bps.sbr.demo.models.User;
import bps.sbr.demo.payloads.requests.SignInRequest;
import bps.sbr.demo.payloads.requests.SignUpRequest;
import bps.sbr.demo.payloads.responses.JWTResponse;
import bps.sbr.demo.payloads.responses.ResponseMessage;
import bps.sbr.demo.repositories.RoleRepository;
import bps.sbr.demo.repositories.UserRepository;
import bps.sbr.demo.securities.jwt.JWTUtils;
import bps.sbr.demo.services.UserDetailsImpl;
import bps.sbr.demo.utils.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = Reference.HOSTING_URL)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JWTUtils jwtUtils;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JWTResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getName(),
                signUpRequest.getNip(),
                signUpRequest.getPhoto(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "supervisor":
                        Role modRole = roleRepository.findByName(ERole.ROLE_SUPERVISOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new ResponseMessage("User created!"));
    }

    @PostMapping("/change-roles")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> changeRoles(@RequestParam(value = "username") String username, @RequestParam(value = "roles") Set<String> roles) {
        System.out.println(username);
        try{
            if (roles.isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(new ResponseMessage("Role kosong"));
            }else{
                Optional<User> _user = userRepository.findByUsername(username.toString());
                User user = new User();
                if(!_user.isPresent()){
                    return ResponseEntity
                            .badRequest()
                            .body(new ResponseMessage("User kosong"));
                }else{
                    user = _user.get();
                }
//                Long user_id = user.getId();
//                roleRepository.deleteUserRoleByID(user_id);
                Set<String> strRoles = roles;
                Set<Role> newRoles = new HashSet<>();
                strRoles.forEach(role -> {
                    System.out.println(role);
                    switch (role) {
                        case "admin":
                            Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            newRoles.add(adminRole);
                            break;
                        case "supervisor":
                            Role modRole = roleRepository.findByName(ERole.ROLE_SUPERVISOR)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            newRoles.add(modRole);
                            break;
                        default:
                            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                            newRoles.add(userRole);
                    }
                });
                System.out.println(newRoles);
                user.setRoles(newRoles);
                userRepository.save(user);
            }
            return ResponseEntity.ok(new ResponseMessage("User created!"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseMessage(e.toString()));
        }
    }
}
