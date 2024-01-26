package io.github.im2back.msclient.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.im2back.msclient.model.Client;
import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.model.ClientResponseDto;
import io.github.im2back.msclient.repository.ClientRepository;
import io.github.im2back.msclient.service.exception.ServiceClientExceptions;

@Service
public class ClientService {

	@Autowired 
	ClientRepository repository;
	
	@Transactional
	public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
		Client clientSave = new Client(clientRequestDto);
		repository.save(clientSave);
		return new ClientResponseDto(clientSave);
	}
	
	public ClientResponseDto findByCpf(String cpf) {
		try {
			Client client = repository.findByCpf(cpf).get();		
			return new ClientResponseDto(client);
		} catch (NoSuchElementException e) {
			throw new ServiceClientExceptions(e.getMessage(),cpf);
		}
		
	}
	
}
