package de.szut.model;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
@Table(name = "supplier_contact")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;

    @OneToMany(mappedBy = "supplier",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    private Set<Article> articles;

}