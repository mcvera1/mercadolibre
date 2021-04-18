package com.service;

import static org.mockito.Mockito.when;

import com.gateway.MutantesGateway;
import com.gateway.model.RegistrarAdn;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MutantTest {

    @InjectMocks
    private Mutant mutant;

    @Mock
    private MutantesGateway mutantesGateway;

    @Test
    public void isMutantTrue() {
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        Boolean isMutante = mutant.isMutant(dna);
        Assert.assertTrue(isMutante);
    }

    @Test
    public void isMutantFalse() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT","AGACGG","GCGTcA","TCACTG"};
        Boolean isMutante = mutant.isMutant(dna);
        Assert.assertFalse(isMutante);
    }

    @Test
    public void obtenerEstadisticas() {
        RegistrarAdn registrarAdn = new RegistrarAdn("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTcA,TCACTG", true);
        List<RegistrarAdn> results = Arrays.asList(registrarAdn);
        when(mutantesGateway.obtenerRegistrosAdn()).thenReturn(results);
        List<RegistrarAdn> resultado = mutantesGateway.obtenerRegistrosAdn();
        Assert.assertTrue(resultado.size() > 0);
    }
}