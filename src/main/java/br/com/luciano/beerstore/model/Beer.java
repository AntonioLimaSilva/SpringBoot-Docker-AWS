package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_beer")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Beer {

    @Id
    @SequenceGenerator(name = "beer_seq", sequenceName = "beer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "beer_seq")
    @EqualsAndHashCode.Include // Estou incluíndo apenas o equals e hash code em cima do id
    private Integer id;
    @NotBlank(message = "beers-1")
    private String name;
    @NotNull(message = "beers-2")
    @Enumerated(EnumType.STRING)
    private BeerType type;
    @NotNull(message = "beers-3")
    @DecimalMin(value = "0", message = "beers-4")
    private BigDecimal volume;
    private String imagePath;

}
