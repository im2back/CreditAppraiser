package io.github.im2back.msclient.model;

import javax.validation.constraints.NotBlank;

public record ClientRequestDto(
		
		@NotBlank
		String cpf,
		
		@NotBlank
		String name,
		
		@NotBlank
		String age) {

}
