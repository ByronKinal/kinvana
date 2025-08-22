package org.algorix.kinvana.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "clientes")
//Lombok
@Data//genera los Setter y Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class ClienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoCliente;
    @Column
    private String nombre;
    private String apellido;
    private String telefono;
    private String correp;
    private String gemero;
    private String edad;
}
