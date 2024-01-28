package io.github.im2back.credit.appraiser.model.clientdtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
	
	private Long id;	
	
	private String name;
	
	private Integer age;
	
}
