package im2back.com.login.authlogin.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {

@NotBlank
private String username;

@NotBlank  @Size(min = 6,max = 20,message = "Password fora dos termos")
private String password;


}
