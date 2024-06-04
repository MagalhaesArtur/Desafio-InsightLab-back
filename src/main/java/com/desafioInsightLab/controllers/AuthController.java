package com.desafioInsightLab.controllers;


import com.desafioInsightLab.domain.user.User;
import com.desafioInsightLab.dtos.*;
import com.desafioInsightLab.infra.security.TokenService;
import com.desafioInsightLab.repositories.UserRepository;
import com.desafioInsightLab.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/validateToken")
    public ResponseEntity authToken(@RequestBody TokenDTO token) {

            var sub = tokenService.validateToken(token.token());
            User user = userRepository.findUserByEmail(sub);
            UserDTO userDTO = new UserDTO(user.getUsername(),user.getEmail(), user.getId());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);


    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthDTO data){
        var usernamePass = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePass);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return  ResponseEntity.ok(new LoginResDTO(token));

    }
    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody RegisterDTO registerDTO){
      if(authService.loadUserByUsername(registerDTO.email()) != null){
          return ResponseEntity.badRequest().build();
      }else{
          String encryptedPass = new BCryptPasswordEncoder().encode(registerDTO.password());
          User newUser =  new User(registerDTO.username(), encryptedPass, registerDTO.email(), registerDTO.role());
          this.authService.createUser(newUser);
          newUser.setPassword(null);
          return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      }
    }
}
