package com.gateway.repositorio;

import com.gateway.model.RegistrarAdn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutanteRepository extends JpaRepository<RegistrarAdn, String> {

}
