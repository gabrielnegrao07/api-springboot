package br.com.curso.apispringboot.apispringboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;

    public EmailDTO(){

    }

    @NotEmpty(message="Preenchimento obrigatório")
    @Email(message="Email inválido")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
