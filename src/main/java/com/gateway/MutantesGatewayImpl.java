package com.gateway;

import com.gateway.model.RegistrarAdn;
import com.gateway.repositorio.MutanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MutantesGatewayImpl implements MutantesGateway {

    @Autowired
    private MutanteRepository mutanteRepository;

    @Override
    public void guardarAdn(List<String> listaAdn, int times) {
        String adn = listaAdn.stream().collect(Collectors.joining(","));
        mutanteRepository.save(new RegistrarAdn(adn, times > 1 ? true : false));
    }

    @Override
    public List<RegistrarAdn> obtenerRegistrosAdn() {
        return mutanteRepository.findAll();
    }
}
