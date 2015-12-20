package io.fourfinanceit.homework.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.fourfinanceit.homework.models.Client;
import io.fourfinanceit.homework.repositories.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client findClient(String name, String surname) {
		return clientRepository.findClientByName(name, surname);
	}

	@Override
	public void saveClient(Client client) {
		clientRepository.save(client);	
	}

}
