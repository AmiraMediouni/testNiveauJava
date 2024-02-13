package com.example.testniveaujava.reposirories;

import com.example.testniveaujava.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    Optional<Client> findByEmail(String email);
    @Override
    List<Client> findAll();
    public Client getClientByEmailAndNom(String email,String nom);
    boolean existsByEmail(String email);


}
