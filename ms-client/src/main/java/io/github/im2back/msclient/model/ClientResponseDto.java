package io.github.im2back.msclient.model;

public record ClientResponseDto(
		Long id,
		
		String cpf,

		String name,

		String age
		
		) {

	public ClientResponseDto(Client clientSave) {
		this(clientSave.getId(),clientSave.getCpf(),clientSave.getName(),clientSave.getAge());
	}

}
