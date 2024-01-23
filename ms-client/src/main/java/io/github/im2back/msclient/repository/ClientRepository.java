package io.github.im2back.msclient.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.im2back.msclient.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByCpf(String cpf);

}
