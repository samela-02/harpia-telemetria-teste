package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface EquipamentoRepositoryMongo extends CrudRepository<Equipamento, String> {
    Optional<Equipamento> findByIdEquipamento(String idEquipamento);
}
