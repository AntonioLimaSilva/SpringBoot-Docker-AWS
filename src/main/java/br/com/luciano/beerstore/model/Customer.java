package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "tb_customer")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @Id
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String email;
    @Column(name = "cell_phone")
    private String cellPhone;

}
