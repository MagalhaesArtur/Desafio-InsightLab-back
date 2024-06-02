package com.desafioInsightLab.controllers;


import com.desafioInsightLab.domain.user.User;
import com.desafioInsightLab.dtos.AuthDTO;
import com.desafioInsightLab.dtos.LoginResDTO;
import com.desafioInsightLab.dtos.RegisterDTO;
import com.desafioInsightLab.infra.security.TokenService;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody  AuthDTO data){
        var usernamePass = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = authenticationManager.authenticate(usernamePass);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return  ResponseEntity.ok(new LoginResDTO(token));

    }
    @PostMapping("/register")
    public  ResponseEntity register(@RequestBody RegisterDTO registerDTO){
      if(authService.loadUserByUsername(registerDTO.username()) != null){
          return ResponseEntity.badRequest().build();
      }else{
          String encryptedPass = new BCryptPasswordEncoder().encode(registerDTO.password());
          User newUser =  new User(registerDTO.username(), encryptedPass, registerDTO.role());
          this.authService.createUser(newUser);
          return new ResponseEntity<>(newUser, HttpStatus.CREATED);
      }
    }
}
