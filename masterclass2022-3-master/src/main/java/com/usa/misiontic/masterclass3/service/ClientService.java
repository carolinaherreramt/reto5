package com.usa.misiontic.masterclass3.service;

import com.usa.misiontic.masterclass3.entities.Admin;
import com.usa.misiontic.masterclass3.entities.Category;
import com.usa.misiontic.masterclass3.entities.Client;
import com.usa.misiontic.masterclass3.repository.AdminRepository;
import com.usa.misiontic.masterclass3.repository.CategoryRepository;
import com.usa.misiontic.masterclass3.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return clientRepository.getClient(id);
    }
    public Client save(Client client){
        if(client.getIdClient()==null) {
            return clientRepository.save(client);
        }else{
            Optional<Client>clientEncontrado=getClient(client.getIdClient());
            if( clientEncontrado.isEmpty()){
                return clientRepository.save(client);
            }else{
                return client;
            }
        }
    }
    public Client update(Client client){
        if (client.getIdClient()!=null){
            Optional<Client> clientEncontrado= getClient(client.getIdClient());
            if(!clientEncontrado.isEmpty()){
                if(client.getName()!=null){
                    clientEncontrado.get().setName(client.getName());
                }
                if (client.getAge()!=null){
                    clientEncontrado.get().setAge(client.getAge());
                }

                if (client.getPassword()!=null) {
                    clientEncontrado.get().setPassword(client.getPassword());
                }

                return clientRepository.save(clientEncontrado.get());

            }
        }
        return client;
    }
    public boolean deleteClient(int id){
        Boolean respuesta= getClient(id).map(client-> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return respuesta;
    }
}
