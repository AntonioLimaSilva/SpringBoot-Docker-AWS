package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_order")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "date_of_create")
    private LocalDateTime dateOfCreate;
    @Column(name = "value_freigth")
    private BigDecimal valueFreigth;
    @Column(name = "value_discount")
    private BigDecimal valueDiscount;
    @Column(name = "value_total")
    private BigDecimal valueTotal;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_customer", foreignKey = @ForeignKey(name = "fk_order_customer"))
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

}
