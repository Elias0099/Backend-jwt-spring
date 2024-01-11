package pe.edu.proyect.interview.crud.login.jwt.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.proyect.interview.crud.login.jwt.dto.SignUpDto;
import pe.edu.proyect.interview.crud.login.jwt.dto.UsuarioDto;
import pe.edu.proyect.interview.crud.login.jwt.entity.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toUserDto(Usuario user);

    @Mapping(target = "password", ignore = true)
    Usuario signUpToUser(SignUpDto signUpDto);

}
