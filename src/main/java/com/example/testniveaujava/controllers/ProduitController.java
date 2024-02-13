package com.example.testniveaujava.controllers;

import com.example.testniveaujava.entites.Produit;
import com.example.testniveaujava.reposirories.ProduitRepository;
import com.example.testniveaujava.responses.MessageResponse;
import com.example.testniveaujava.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value ="/produits" )
public class ProduitController {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return this.produitService.getAllProduits();
    }

    @GetMapping(value = "/{id}")
    public Produit getProduitByID(@PathVariable("id") int id) {
        return this.produitService.getProduitByID(id);
    }

    @PostMapping
    public Produit addNewProduit(@RequestBody Produit produit) {
        return this.produitService.addNewProduit(produit);
    }

    @PutMapping(value = "/{id}")
    public Produit updateProduit(@PathVariable("id") int id, @RequestBody Produit produit) {
        return produitService.updateProduitByID(id, produit);
    }

    @DeleteMapping(value = "/{id}")
    public MessageResponse deleteProduit(@PathVariable("id") int id) {
        return this.produitService.deleteProduitById(id);
    }



}
