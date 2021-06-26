package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.misc.ClientNonConfidential;
import simple.app.model.Client;
import simple.app.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients()
    {
        List<Client> clientRecords = new ArrayList<>();
        clientRepository.findAll().forEach(clientRecords::add);
        return clientRecords;
    }

    public List<ClientNonConfidential> getAllClientsNonConfidential(){
        List<ClientNonConfidential> clients = new ArrayList<>();
        clientRepository.getClientsNonConfidential().forEach(clients::add);
        return clients;
    }

    public Client getClientByLogin(String login) {
        return clientRepository.findByClientLogin(login);
    }

    public void AddClient(Client client)
    {
        clientRepository.save(client);
    }
}