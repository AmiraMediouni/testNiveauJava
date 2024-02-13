package com.example.testniveaujava.reposirories;

import com.example.testniveaujava.entites.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Integer> {
    @Override
    List<Commande> findAll();


}
