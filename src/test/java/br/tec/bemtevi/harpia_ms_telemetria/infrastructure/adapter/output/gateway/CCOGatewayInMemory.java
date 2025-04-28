package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.Role;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class CCOGatewayInMemory implements CCOGateway {
    private final List<Usuario> usuarios;

    public CCOGatewayInMemory() {
        usuarios = new ArrayList<>();
        Usuario usuarioAdmin = new Usuario(1L, "admin", "BTV", Role.ADMINISTRADOR);
        Usuario usuarioCoordenador = new Usuario(2L, "coordenador", "BTV", Role.COORDENADOR_OPERACAO);
        Usuario usuarioOpCentral = new Usuario(3L, "opcentral", "BTV", Role.OPERADOR_CENTRAL);
        Usuario usuarioOpCampo = new Usuario(4L, "opcampo", "BTV", Role.OPERADOR_CAMPO);
        usuarios.addAll(asList(usuarioAdmin, usuarioCoordenador, usuarioOpCentral, usuarioOpCampo));
    }

    @Override
    public Usuario findUserInfo(String bearerToken) {
        return usuarios
                .stream()
                .filter(usuario -> usuario.getNmUsuario().equals(bearerToken))
                .findFirst()
                .get();
    }
}