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
    private Integer id;
    @Column
    private String titulo;
    private String autor;
    private String telefono;
    private String genero;
    private String cantidad;
}
