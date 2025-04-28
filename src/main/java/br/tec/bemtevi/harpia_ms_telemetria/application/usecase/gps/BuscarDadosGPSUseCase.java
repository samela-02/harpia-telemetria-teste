package br.tec.bemtevi.harpia_ms_telemetria.application.usecase.gps;

import br.tec.bemtevi.harpia_ms_telemetria.domain.enums.Role;
import br.tec.bemtevi.harpia_ms_telemetria.domain.exception.PermissaoException;
import br.tec.bemtevi.harpia_ms_telemetria.domain.gateway.CCOGateway;
import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;
import br.tec.bemtevi.harpia_ms_telemetria.domain.storage.GPSStorage;
import org.springframework.stereotype.Service;

@Service
public class BuscarDadosGPSUseCase {
    private final CCOGateway ccoGateway;
    private final GPSStorage gpsStorage;

    public BuscarDadosGPSUseCase(CCOGateway ccoGateway, GPSStorage gpsStorage) {
        this.ccoGateway = ccoGateway;
        this.gpsStorage = gpsStorage;
    }

    public Object execute(String bearerToken, String idInstituicao) {
        Usuario usuario = findUsuarioLogado(bearerToken);
        validarPermissoes(usuario);
        idInstituicao = ajustarIdInstituicaoBaseadoNoUsuarioLogado(idInstituicao, usuario);
        return gpsStorage.findGpsData(idInstituicao);
    }

    private Usuario findUsuarioLogado(String bearerToken) {
        return ccoGateway.findUserInfo(bearerToken);
    }

    private void validarPermissoes(Usuario usuario) {
        if (!(usuario.getRole().equals(Role.ADMINISTRADOR) ||
                usuario.getRole().equals(Role.COORDENADOR_OPERACAO) ||
                usuario.getRole().equals(Role.OPERADOR_CENTRAL)))
            throw new PermissaoException("O usuário não tem permissões para buscar dados de gps.");
    }

    private String ajustarIdInstituicaoBaseadoNoUsuarioLogado(String idInstituicao, Usuario usuario) {
        if (usuario.getRole().equals(Role.ADMINISTRADOR))
            return idInstituicao;
        return usuario.getIdInstituicao();
    }
}
