package org.ub.dapp02practica04.models;

import javax.persistence.*;
import java.util.UUID;

@Entity()
@Table(name = "clientes")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String name;
}
