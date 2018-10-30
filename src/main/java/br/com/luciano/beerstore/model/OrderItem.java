package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_order_item")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {

    @Id
    @SequenceGenerator(name = "order_item_seq", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_seq")
    @EqualsAndHashCode.Include
    private Integer id;
    private BigDecimal price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_beer", foreignKey = @ForeignKey(name = "fk_order_item_beer"))
    private Beer beer;
    @ManyToOne
    @JoinColumn(name = "id_order", foreignKey = @ForeignKey(name = "fk_order_item_order"))
    private Order order;

}
