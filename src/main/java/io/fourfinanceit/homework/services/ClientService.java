package io.fourfinanceit.homework.services;

import io.fourfinanceit.homework.models.Client;

public interface ClientService {
	Client findClient(String name, String surname);
	void saveClient(Client client);
}
