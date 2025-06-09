package org.ub.dapp02practica04;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RepositoryEmpleado extends JpaRepository<Empleado, UUID> {

    Optional<Empleado> findByAddress( String address );
}
