package com.gateway;

import com.gateway.model.RegistrarAdn;
import com.gateway.repositorio.MutanteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MutantesGatewayImplTest {

    @InjectMocks
    private MutantesGatewayImpl mutantesGateway;

    @Mock
    private MutanteRepository mutanteRepository;

    @Test
    public void guardarAdn() {
        List<String> listaAdn = Arrays.asList("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTcA,TCACTG");

        mutantesGateway.guardarAdn(listaAdn,2);
        Assert.assertEquals(listaAdn.size(), 1);
    }

    @Test
    public void obtenerRegistrosAdn() {
        when(mutanteRepository.findAll()).thenReturn(Arrays.asList(new RegistrarAdn("ATGCGA,CAGTGC,TTATTT,AGACGG,GCGTcA,TCACTG", true)));
        List<RegistrarAdn> resultado = mutantesGateway.obtenerRegistrosAdn();
        Assert.assertTrue(resultado.size() > 0);
    }
}