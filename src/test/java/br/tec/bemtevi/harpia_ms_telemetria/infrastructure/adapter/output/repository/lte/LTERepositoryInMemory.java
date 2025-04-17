package br.tec.bemtevi.harpia_ms_telemetria.infrastructure.adapter.output.repository.lte;

import br.tec.bemtevi.harpia_ms_telemetria.domain.model.LTE;
import br.tec.bemtevi.harpia_ms_telemetria.domain.repository.LTERepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LTERepositoryInMemory implements LTERepository {
    private final List<LTE> lteList;

    public LTERepositoryInMemory() {
        lteList = new ArrayList<>();
    }

    @Override
    public void save(LTE lte) {
        UUID uuid = UUID.randomUUID();
        LTE novo = new LTE(uuid.toString(),
                "name",
                0.0,
                "nmcarrier",
                "nminternalstate",
                "nmsimcardstate",
                "nmstatus",
                null);
        lteList.add(novo);
    }
}