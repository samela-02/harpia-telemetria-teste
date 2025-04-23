package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.service;

import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.Role;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.domain.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SpringUsuarioService implements UsuarioService {
    @Override
    public Usuario findUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String[] informacoesDoUsuario = getInformacoesDoUsuario(authentication.getPrincipal());
        Role role = getRoleDoUsuario(authentication.getAuthorities());
        String nmUsuario = informacoesDoUsuario[0];
        String idInstituicao = informacoesDoUsuario[1];
        return new Usuario(null, nmUsuario, idInstituicao, role);
    }

    private String[] getInformacoesDoUsuario(Object principal) {
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername().split("/");
        }
        return principal.toString().split("/");
    }

    private Role getRoleDoUsuario(Collection<? extends GrantedAuthority> authorities) {
        GrantedAuthority grantedAuthority = authorities.stream().findFirst().get();
        String authority = grantedAuthority.getAuthority();
        String roleString = authority.substring(5);
        return Role.fromString(roleString);
    }
}
