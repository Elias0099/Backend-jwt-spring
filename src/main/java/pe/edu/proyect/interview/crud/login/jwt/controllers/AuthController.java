package pe.edu.proyect.interview.crud.login.jwt.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.proyect.interview.crud.login.jwt.config.UserAuthenticationProvider;
import pe.edu.proyect.interview.crud.login.jwt.dto.CredentialsDto;
import pe.edu.proyect.interview.crud.login.jwt.dto.SignUpDto;
import pe.edu.proyect.interview.crud.login.jwt.dto.UsuarioDto;
import pe.edu.proyect.interview.crud.login.jwt.services.UsuarioService;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final UsuarioService usuarioService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        UsuarioDto usuarioDto = usuarioService.login(credentialsDto);
        usuarioDto.setToken(userAuthenticationProvider.createToken(usuarioDto.getLogin()));
        return ResponseEntity.ok(usuarioDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDto> register(@RequestBody @Valid SignUpDto user) {
        UsuarioDto createdUser = usuarioService.register(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
