package br.com.luciano.beerstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @EqualsAndHashCode.Include
    private Integer id;
    @NotBlank(message = "user-1")
    @Size(max = 100, message = "user-2")
    private String username;
    @NotBlank(message = "user-3")
    @Size(max = 100, message = "user-4")
    private String password;
    @NotBlank(message = "user-5")
    @Size(max = 100, message = "user-6")
    private String email;
    private Boolean active = Boolean.FALSE;
    @Valid
    @NotNull(message = "user-7")
    @Size(min = 1, message = "user-8")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_group", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    protected List<Group> groups;

}
