package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "customer-1")
    private String name;
    @NotBlank(message = "customer-2")
    private String email;
    @NotBlank(message = "customer-3")
    @Column(name = "cell_phone")
    private String cellphone;

}
