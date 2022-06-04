package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.models.ERole;
import com.noranekoit.challenge4.models.entity.Role;
import com.noranekoit.challenge4.models.entity.Users;
import com.noranekoit.challenge4.payload.request.InsertUserRequest;
import com.noranekoit.challenge4.payload.request.LoginRequest;
import com.noranekoit.challenge4.payload.request.UpdateUserRequest;
import com.noranekoit.challenge4.payload.response.MessageResponse;
import com.noranekoit.challenge4.payload.response.UserInfoResponse;
import com.noranekoit.challenge4.payload.response.WebResponse;
import com.noranekoit.challenge4.repository.RoleRepository;
import com.noranekoit.challenge4.repository.UsersRepository;
import com.noranekoit.challenge4.security.jwt.JwtUtils;
import com.noranekoit.challenge4.security.services.UserDetailsImpl;
import com.noranekoit.challenge4.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UsersController {

    AuthenticationManager authenticationManager;

    UsersRepository usersRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    private final UsersService usersService;


    @Autowired
    public UsersController(AuthenticationManager authenticationManager, UsersRepository usersRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils, UsersService usersService) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.usersService = usersService;
    }

    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @GetMapping("/allUser")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<Stream<UserInfoResponse>> getAllUsers() {
        Stream<UserInfoResponse> userResponse = usersService.getAllUsers();
        return new WebResponse<>(
                200,
                "Ok",
                userResponse
        );
    }


    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody InsertUserRequest signUpRequest) {
        if (Boolean.TRUE.equals(usersRepository.existsByUsername(signUpRequest.getUsername()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (Boolean.TRUE.equals(usersRepository.existsByEmail(signUpRequest.getEmail()))) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        Users newUser = new Users(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail()
                );

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
                                .orElseThrow(() -> new RuntimeException("Error: Role ADMIN is not found."));
                        roles.add(adminRole);

                        break;
                    case "cus":
                        Role modRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                                .orElseThrow(() -> new RuntimeException("Error: Role CUSTOMER is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });

        }

        newUser.setRoles(roles);
        usersRepository.save(newUser);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<MessageResponse> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }

    @Operation(description = "perlu Role CUSTOMER dan ADMIN silahkan login customer/admin untuk dapat cookies barer token")
    @PutMapping("/updateUser/{idUser}")
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public WebResponse<UserInfoResponse> updateUser(
            @PathVariable Long idUser,
            @RequestBody UpdateUserRequest updateUserRequest) throws NotFoundException {
        UserInfoResponse userResponse = usersService.updateUser(idUser, updateUserRequest);
        return new WebResponse<>(
                200,
                "OK",
                userResponse
        );

    }

    @Operation(description = "perlu Role ADMIN silahkan login admin untuk dapat cookies barer token")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{idUser}")
    public WebResponse<String> deleteUser(@PathVariable Long idUser) {
        usersService.deleteUserByUsername(idUser);
        return new WebResponse<>(
                200,
                "OK",
                idUser + " Berhasil Di hapus"
        );
    }


}