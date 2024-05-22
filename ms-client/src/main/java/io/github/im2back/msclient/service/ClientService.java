package io.github.im2back.msclient.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.im2back.msclient.model.Client;
import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.model.ClientResponseDto;
import io.github.im2back.msclient.model.validations.ClientRegistrationValidator;
import io.github.im2back.msclient.repository.ClientRepository;
import io.github.im2back.msclient.service.exception.ServiceClientExceptions;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private List<ClientRegistrationValidator> validationsRegister;

	@Transactional
	public ClientResponseDto saveClient(ClientRequestDto clientRequestDto) {
		validationsRegister.forEach(v -> v.valid(clientRequestDto));
		Client clientSave = new Client(clientRequestDto);
		repository.save(clientSave);
		return new ClientResponseDto(clientSave);
	}

	@Transactional(readOnly = true)
	public ClientResponseDto findByCpf(String cpf) {
		Client client = repository.findByCpf(cpf).orElseThrow(() -> new ServiceClientExceptions(cpf));
		return new ClientResponseDto(client);
	}
}
