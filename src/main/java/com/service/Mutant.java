package com.service;

import com.controller.dto.EstadisticasDto;
import com.gateway.MutantesGateway;
import com.gateway.model.RegistrarAdn;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Mutant implements MutantService{

    private MutantesGateway mutantesGateway;

    public Mutant(MutantesGateway mutantesGateway) {
        this.mutantesGateway = mutantesGateway;
    }

    /**
     * convierte la cadena de dna en una matrix para recorrer las filas,
     * columnas y diagonales y validar si el adn es de un mutante
     * @param dna
     * @return
     */
    public Boolean isMutant(String[] dna) {

        List<String> secuenciaAdn = Arrays.asList("AAAA", "TTTT", "CCCC", "GGGG");

        List<String> listaAdn = Arrays.asList(dna);
        String[][] array = new String[listaAdn.size()][listaAdn.get(0).length()];
        for (int i = 0; i < listaAdn.size(); i++) {
            for (int j = 0; j < listaAdn.get(i).length(); j++) {
                array[i][j] = String.valueOf(listaAdn.get(i).charAt(j));
            }
        }
        int times = 0;
        times = secuenciaAdn.stream().map(adn -> {
            int secuenciaEncontrada = 0;
            secuenciaEncontrada += validarVertical(array, adn);
            secuenciaEncontrada += validarHorizontal(array, adn);
            secuenciaEncontrada += validarDiagonal(array, adn);
            return secuenciaEncontrada;
        }).collect(Collectors.summingInt(Integer::intValue));
        mutantesGateway.guardarAdn(listaAdn, times);
        return times > 1 ? true : false;
    }

    @Override
    public EstadisticasDto obtenerEstadisticas() {
        List<RegistrarAdn> resultado = mutantesGateway.obtenerRegistrosAdn();
        long countMutanDna = resultado.stream().filter(dna -> dna.isMutant()).collect(Collectors.counting());
        long countHumanDna = resultado.stream().filter(dna -> !dna.isMutant()).collect(Collectors.counting());
        float resultadoRatio = countMutanDna/countHumanDna;

        return new EstadisticasDto(countMutanDna, countHumanDna, resultadoRatio);
    }

    /**
     * Valida las filas de la matriz para validar
     * si concuerda con la secuencia de ADN de
     * forma vertical
     * @param array
     * @param secuenciaAdn
     * @return
     */
    private int validarVertical(String[][] array, String secuenciaAdn) {
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            StringBuilder cadenaAdnVertical = new StringBuilder();
            for (int j = 0; j < array[i].length; j++) {
                cadenaAdnVertical.append(array[i][j]);
            }
            if (cadenaAdnVertical.indexOf(secuenciaAdn) > -1) {
                times ++;
            }
        }
        return times;
    }

    /**
     * Valida las filas de la matriz para validar
     * si concuerda con la secuencia de ADN de
     * forma horizontal
     * @param array
     * @param secuenciaAdn
     * @return
     */
    private int validarHorizontal(String[][] array, String secuenciaAdn) {
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            StringBuilder cadenaAdnHorizontal = new StringBuilder();
            for (int j = 0; j < array[i].length; j++) {
                cadenaAdnHorizontal.append(array[j][i]);
            }
            if (cadenaAdnHorizontal.indexOf(secuenciaAdn) > -1) {
                times ++;
            }

        }
        return times;
    }

    /**
     * Valida las filas de la matriz para validar
     * si concuerda con la secuencia de ADN de
     * forma diagonal
     * @param array
     * @param secuenciaAdn
     * @return
     */
    private int validarDiagonal(String[][] array, String secuenciaAdn) {
        int times = 0;
        for (int i = 1 - array.length; i < array.length - 1; i++) {
            StringBuilder cadenaAdnDiagonal = new StringBuilder();
            for (int j = Math.max(0, i), horizontal = -Math.min(0, i); j < array.length && horizontal < array[0].length; j++, horizontal++) {
                cadenaAdnDiagonal.append(array[j][horizontal]);
            }
            if (cadenaAdnDiagonal.indexOf(secuenciaAdn) > -1) {
                times ++;
            }
        }
        return times;
    }

    private void otraDiagoal(String[][] array, String secuenciaAdn) {

        int a, horizontal = array.length;
        for (int i = 1 - array.length; i < array.length - 1; i++) {
            StringBuilder cadenaAdnDiagonal = new StringBuilder();
            if(horizontal == 0) {

            } else {
                for (a = Math.max(array.length - 1, i), horizontal = -Math.min(0, i); a >= 0 && horizontal < array[0].length; a--, horizontal++) {
                    cadenaAdnDiagonal.append(array[a][horizontal]);
                }
            }
            if (cadenaAdnDiagonal.indexOf(secuenciaAdn) > -1) {
                System.err.println(cadenaAdnDiagonal);
            }

        }
    }

}
