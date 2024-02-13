package com.example.testniveaujava.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Produit {
    @Id
    @GeneratedValue
    private int id;
    private  String nom;
    private  String description;
    private  Integer quantite;
    private Float prix;
    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;

}
