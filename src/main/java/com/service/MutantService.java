package com.service;

import com.controller.dto.EstadisticasDto;

public interface MutantService {
    Boolean isMutant(String[] dna);

    EstadisticasDto obtenerEstadisticas();
}
