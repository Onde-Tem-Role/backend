package org.generation.ondetemrole.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.generation.ondetemrole.model.UsuarioLogin;
import org.apache.commons.codec.binary.Base64;
import org.generation.ondetemrole.model.Usuario;
import org.generation.ondetemrole.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario>cadastrarUsuario(Usuario usuario){
			
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
		return Optional.empty();
		}
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));
	}
	
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin>usuarioLogin){
		Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(compararSenhas(usuarioLogin.get().getSenha(),usuario.get().getSenha())) {
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setFoto(usuario.get().getFoto());
				usuarioLogin.get().setToken(geradorBasicToken(usuarioLogin.get().getUsuario(),usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setTipo(usuario.get().getTipo());
				
				return usuarioLogin;
			}
		}
		
		return Optional.empty();
	}
	
	private boolean compararSenhas(String senhaDigitada,String senhaDoBanco) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senhaDigitada, senhaDoBanco);
	}
	
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	
	private String geradorBasicToken(String usuario, String senha) {
		String token = usuario + ":" + senha;
		
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		
		return "Basic " + new String(tokenBase64);
	}
	
	public Optional<Usuario> atualizarUsuario (Usuario usuario) {
        if (usuarioRepository.findById(usuario.getId()).isPresent()) {

            Optional<Usuario> cadeUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
            if ((cadeUsuario.isPresent())&& (cadeUsuario.get().getId() != usuario.getId()))
                    throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Já existente",null);

        usuario.setSenha(criptografarSenha(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Usuario não encontrado",null);
}
}
