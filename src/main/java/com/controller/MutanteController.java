package com.controller;

import com.controller.dto.DnaDto;
import com.controller.dto.EstadisticasDto;
import com.excepciones.ForbidenException;
import org.springframework.web.bind.annotation.*;
import com.service.MutantService;

@RestController
@RequestMapping("api")
public class MutanteController {

    private MutantService mutantService;

    public MutanteController(MutantService mutantService) {
        this.mutantService = mutantService;
    }

    @PostMapping("/mutant")
    public void validarInformacionDna(@RequestBody DnaDto request) {
        if(!mutantService.isMutant(request.getDna())) {
            throw new ForbidenException("no mutante");
        }
    }

    @GetMapping("/stats")
    public EstadisticasDto obtenerInformacionDna() {
        return mutantService.obtenerEstadisticas();
    }
}
