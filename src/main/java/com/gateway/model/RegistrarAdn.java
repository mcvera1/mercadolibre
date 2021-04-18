package com.gateway.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RegistrarAdn {
    @Id
    private String adn;
    private boolean mutant;

    public RegistrarAdn() {

    }

    public RegistrarAdn(String adn, boolean mutant){
        this.adn = adn;
        this.mutant = mutant;
    }

    public String getAdn() {
        return adn;
    }

    public void setAdn(String adn) {
        this.adn = adn;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
