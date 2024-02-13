package com.example.testniveaujava.services;

import com.example.testniveaujava.entites.Produit;
import com.example.testniveaujava.reposirories.ProduitRepository;
import com.example.testniveaujava.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;
    public List<Produit> produitList=new CopyOnWriteArrayList<>();
    private int produitIdCount=1;
    public List<Produit> getAllProduits(){
         return produitRepository.findAll();
    }

    public Produit getProduitByID(int id){
         return produitRepository.findById(id).get();
    }

    public Produit addNewProduit(Produit produit){
        return produitRepository.save(produit);
    }

    public Produit updateProduitByID(int id, Produit produit){
        produit.setId(id);
        return produitRepository.save(produit);

    }
    public MessageResponse deleteProduitById(int id ){
        produitRepository.deleteById(id);
        return new MessageResponse("Produit deleted with success!");


    }
}
