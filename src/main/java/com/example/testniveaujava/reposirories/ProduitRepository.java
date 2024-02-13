package com.example.testniveaujava.reposirories;


import com.example.testniveaujava.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    @Override
    List<Produit> findAll();


}
