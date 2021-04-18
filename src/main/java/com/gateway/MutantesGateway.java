package com.gateway;

import com.gateway.model.RegistrarAdn;

import java.util.List;

public interface MutantesGateway {
    void guardarAdn(List<String> listaAdn, int times);

    List<RegistrarAdn> obtenerRegistrosAdn();
}
