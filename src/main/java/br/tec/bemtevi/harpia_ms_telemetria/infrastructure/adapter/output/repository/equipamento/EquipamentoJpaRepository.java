package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.equipamento;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface EquipamentoJpaRepository extends JpaRepository<Equipamento, Long> {
    Optional<Equipamento> findByIdEquipamento(String idEquipamento);
}
