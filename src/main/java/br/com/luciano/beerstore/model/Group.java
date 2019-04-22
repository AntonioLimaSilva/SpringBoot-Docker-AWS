package br.com.luciano.beerstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "tb_group")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Group {

    @Id
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @EqualsAndHashCode.Include
    private Integer id;
    @NotBlank(message = "group-1")
    private String name;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_group_role", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

}
