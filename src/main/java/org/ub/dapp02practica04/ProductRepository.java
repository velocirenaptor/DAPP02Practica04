package org.ub.dapp02practica04;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ub.dapp02practica04.models.Producto;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Producto, UUID> {

}
