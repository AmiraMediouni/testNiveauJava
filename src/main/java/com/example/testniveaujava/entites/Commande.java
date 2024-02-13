package com.example.testniveaujava.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Entity
public class Commande {
    @Id
    @GeneratedValue
    private int id;
    private  Float prix_total;
    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;
    @OneToMany(mappedBy="commande")
    private List <Produit> produits;


}
