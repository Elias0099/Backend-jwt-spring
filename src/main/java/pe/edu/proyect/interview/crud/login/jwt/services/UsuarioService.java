package pe.edu.proyect.interview.crud.login.jwt.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.proyect.interview.crud.login.jwt.dto.CredentialsDto;
import pe.edu.proyect.interview.crud.login.jwt.dto.SignUpDto;
import pe.edu.proyect.interview.crud.login.jwt.dto.UsuarioDto;
import pe.edu.proyect.interview.crud.login.jwt.entity.Usuario;
import pe.edu.proyect.interview.crud.login.jwt.exceptions.AppException;
import pe.edu.proyect.interview.crud.login.jwt.mappers.UsuarioMapper;
import pe.edu.proyect.interview.crud.login.jwt.repositories.UsuarioRepository;

import java.nio.CharBuffer;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper UserMapper;

    public UsuarioDto login(CredentialsDto credentialsDto) {
        Usuario usuario = usuarioRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), usuario.getPassword())) {
            return UserMapper.toUserDto(usuario);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UsuarioDto register(SignUpDto userDto) {
        Optional<Usuario> optionalUser = usuarioRepository.findByLogin(userDto.getLogin());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = UserMapper.signUpToUser(userDto);
        usuario.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

        Usuario savedUsuario = usuarioRepository.save(usuario);

        return UserMapper.toUserDto(savedUsuario);
    }

    public UsuarioDto findByLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return UserMapper.toUserDto(usuario);
    }

}
