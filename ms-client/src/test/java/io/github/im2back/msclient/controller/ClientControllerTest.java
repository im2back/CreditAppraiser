package io.github.im2back.msclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import io.github.im2back.msclient.model.ClientRequestDto;
import io.github.im2back.msclient.model.ClientResponseDto;
import io.github.im2back.msclient.service.ClientService;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ClientControllerTest {

	@MockBean
	private ClientService service;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<ClientRequestDto> clientRequestDto;

	@Autowired
	private JacksonTester<ClientResponseDto> clientResponseDto;

	@Test
	@DisplayName("Deveria retornar o status 201 e o DTO do objeto criado")
	void saveClient() throws IOException, Exception {

		// ARRANGE
		ClientRequestDto jsonRequest = new ClientRequestDto("008.602.024-14", "Ana paula", "20");
		ClientResponseDto jsonResponse = new ClientResponseDto(1l, "008.602.024-14", "Ana paula", "20");

		var jsonEsperado = this.clientResponseDto.write(jsonResponse).getJson();

		when(service.saveClient(jsonRequest)).thenReturn(jsonResponse);

		// ACT + ASSERT(status e URI)
		var response = mvc
				.perform(post("/clients").contentType(MediaType.APPLICATION_JSON)
						.content(this.clientRequestDto.write(jsonRequest).getJson()))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", Matchers.endsWith("/clients/" + jsonResponse.id()))).andReturn()
				.getResponse();

		// ASSERT
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

	}

	@Test
	@DisplayName("Deveria retornar o status 200 ok e o cpf do parametro deve ser igual ao do DTO retornado")
	void getClientByCpf() throws Exception {
		// ARRANGE
		String cpf = "008.602.024-14";
		ClientResponseDto jsonResponse = new ClientResponseDto(1l, "008.602.024-14", "Ana paula", "20");

		when(service.findByCpf(cpf)).thenReturn(jsonResponse);

		// ACT
		var response = mvc.perform(get("/clients").param("cpf", cpf)).andExpect(status().isOk()).andReturn().getResponse();

		// ASSERT
		ClientResponseDto responseConvert = this.clientResponseDto.parseObject(response.getContentAsString());
		Assertions.assertEquals(cpf, responseConvert.cpf());

	}
}
