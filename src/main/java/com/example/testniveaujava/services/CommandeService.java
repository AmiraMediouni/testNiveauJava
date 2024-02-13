package com.example.testniveaujava.services;


import com.example.testniveaujava.entites.Commande;
import com.example.testniveaujava.entites.Produit;
import com.example.testniveaujava.reposirories.CommandeRepository;
import com.example.testniveaujava.reposirories.ProduitRepository;
import com.example.testniveaujava.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class CommandeService {
    @Autowired
    CommandeRepository commandeRepository;
    ProduitRepository produitRepository;
    public List<Commande> commandeList=new CopyOnWriteArrayList<>();
    private int commandeIdCount=1;
    public List<Commande> getAllCommandes(){
         return commandeRepository.findAll();
    }

    public Commande getCommandeByID(int id){
         return commandeRepository.findById(id).get();
    }

    public Commande updateCommandeByID(int id, Commande commande){
        commande.setId(id);
        return commandeRepository.save(commande);

    }
    public MessageResponse deleteCommandeById(int id ){
        commandeRepository.deleteById(id);
        return new MessageResponse("Commande deleted with success!");


    }
    public float getPrixTotal(List<Produit> produitList) {

        float prixTotal = 0;
        float prix = 0;
        int quantite = 0;

        for (Produit produit : produitList) {

            int productId = produit.getId();
            Optional<Produit> product = produitRepository.findById(productId);
            if (product.isPresent()) {
                Produit product1 = product.get();
                if (product1.getQuantite() < produit.getQuantite()) {
                    prix = product1.getPrix() * product1.getQuantite();
                    produit.setQuantite(product1.getQuantite());
                } else {
                    prix = produit.getQuantite() * product1.getPrix();
                    quantite = product1.getQuantite() - produit.getQuantite();
                }
                prixTotal = prixTotal + prix;
                product1.setQuantite(quantite);
                quantite=0;
                produit.setNom(product1.getNom());
                produit.setPrix(prix);
                produitRepository.save(product1);
            }
        }
        return prixTotal;
    }
    public Commande addNewCommande(Commande commande){
        return commandeRepository.save(commande);
    }


}
