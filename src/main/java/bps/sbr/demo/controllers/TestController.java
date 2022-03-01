package bps.sbr.demo.controllers;

import bps.sbr.demo.models.SBRTemp;
import bps.sbr.demo.models.User;
import bps.sbr.demo.repositories.UserRepository;
import bps.sbr.demo.securities.jwt.JWTUtils;
import bps.sbr.demo.utils.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@CrossOrigin(origins = Reference.HOSTING_URL)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/all")
    public ResponseEntity<?> allAccess(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String headerAuth = request.getHeader("Authorization");
            if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
                String token = headerAuth.substring(7, headerAuth.length());
                Optional<User> user = userRepository.findByUsername(jwtUtils.getUserNameFromJwtToken(token));
                User _user = user.get();
                response.put("username", _user.getUsername());
                response.put("email", _user.getEmail());
                response.put("name", _user.getName());
                response.put("nip", _user.getNip());
                response.put("token", token);
                return new ResponseEntity(response, HttpStatus.OK);
            }else{
                return new ResponseEntity(null, HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('SUPERVISOR') or hasRole('ADMIN')")
    public String userAccess() {

        return "User Content.";
    }
    @GetMapping("/supervisor")
    @PreAuthorize("hasRole('SUPERVISOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}