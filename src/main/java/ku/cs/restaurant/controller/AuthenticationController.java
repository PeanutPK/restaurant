package ku.cs.restaurant.controller;

import jakarta.validation.Valid;
import ku.cs.restaurant.dto.LoginRequest;
import ku.cs.restaurant.dto.SignupRequest;
import ku.cs.restaurant.security.JwtUtil;
import ku.cs.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtils;
    private static final String AUTH_COOKIE_NAME = "token";

    @Autowired
    public AuthenticationController(UserService userService,
                                    AuthenticationManager authenticationManager, JwtUtil jwtUtils) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();


        String token = jwtUtils.generateToken(userDetails.getUsername());


        // Create HttpOnly cookie
        ResponseCookie cookie = ResponseCookie.from(AUTH_COOKIE_NAME, token)
                .httpOnly(true)          // Javascript cannot read cookie
                .secure(true)            // HTTPS only
                .path("/")
                .maxAge(60 * 60)         // 1 hour
                .sameSite("Strict")
                .build();


        // Return cookie in response headers, optional JSON body
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(Map.of(
                        "message", "Successfully logged in"
                ));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignupRequest request) {
        if (userService.userExists(request.getUsername()))
            return new ResponseEntity<>("Error: Username is already taken!", HttpStatus.BAD_REQUEST);

        userService.createUser(request);
        return ResponseEntity.ok("User registered successfully!");
    }

}
