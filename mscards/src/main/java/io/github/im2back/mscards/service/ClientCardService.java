package io.github.im2back.mscards.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.im2back.mscards.model.clientcard.ClientCard;
import io.github.im2back.mscards.model.clientcard.ClientCardResponseDto;
import io.github.im2back.mscards.repository.ClientCardRepository;

@Service
public class ClientCardService {

	@Autowired
	private ClientCardRepository repository;
	
	@Transactional(readOnly = true)
	public List<ClientCardResponseDto> listCardByCpf(String cpf) {
		List<ClientCard> listClientCard = repository.findByCpf(cpf);
		List<ClientCardResponseDto> listDto = new ArrayList<>();

		for (ClientCard element : listClientCard) {
			var listClientCardDto = new ClientCardResponseDto(element);
			listDto.add(listClientCardDto);
		}
		return listDto;
	}

}
