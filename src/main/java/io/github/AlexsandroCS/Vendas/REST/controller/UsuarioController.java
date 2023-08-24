package io.github.AlexsandroCS.Vendas.REST.controller;

import io.github.AlexsandroCS.Vendas.REST.DTO.RegistroUsuarioDTO;
import io.github.AlexsandroCS.Vendas.REST.DTO.UsuarioDTO;
import io.github.AlexsandroCS.Vendas.domain.entity.Usuario;
import io.github.AlexsandroCS.Vendas.domain.repository.Usuarios;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class UsuarioController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    Usuarios usuariosRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioDTO dadosLogin){
        var senhaDoUsuario = new UsernamePasswordAuthenticationToken(dadosLogin.login(), dadosLogin.senha());
        var auth = this.authenticationManager.authenticate(senhaDoUsuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegistroUsuarioDTO dadosRegistro){
        if (this.usuariosRepository.findByLogin(dadosRegistro.login()) != null){
            return ResponseEntity.badRequest().build();
        }

        String senhaCriptografada = new BCryptPasswordEncoder().encode(dadosRegistro.senha());
        Usuario registrandoUsuario = new Usuario(dadosRegistro.login(), senhaCriptografada, dadosRegistro.tipoUsuario());

        this.usuariosRepository.save(registrandoUsuario);

        return ResponseEntity.ok().build();
    }
}
