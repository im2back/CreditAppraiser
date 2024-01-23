package io.github.im2back.mscards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.im2back.mscards.model.clientcard.ClientCard;

public interface ClientCardRepository extends JpaRepository<ClientCard,Long> {
	
	List<ClientCard> findByCpf(String cpf);
	
}
