package com.example.testniveaujava.services;

import com.example.testniveaujava.entites.Client;
import com.example.testniveaujava.entites.Role;
import com.example.testniveaujava.reposirories.ClientRepository;
import com.example.testniveaujava.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public List<Client> clientList=new CopyOnWriteArrayList<>();
    private int clientIdCount=1;
    public List<Client> getAllClients(){
         return clientRepository.findAll();
    }

    public Client getClientByID(int id){
         return clientRepository.findById(id).get();
    }

    public Client addNewClient(Client client){
        client.setRole(Role.CLIENT);
        return clientRepository.save(client);
    }

    public Client updateClientByID(int id, Client client){
        client.setId(id);
        return clientRepository.save(client);

    }
    public MessageResponse deleteClientById(int id ){
        clientRepository.deleteById(id);
        return new MessageResponse("Client deleted with success!");


    }
    public Integer isClientPresent(Client client){
        Client client1 = clientRepository.getClientByEmailAndNom(client.getEmail(),client.getNom());
        return client1!=null ? client1.getId(): null ;
    }
}
