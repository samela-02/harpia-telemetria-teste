package br.tec.bemtevi.harpia_ms_telemetria.domain.gateway;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Usuario;

public interface CCOGateway {
    Usuario findUserInfo(String bearerToken);
}
