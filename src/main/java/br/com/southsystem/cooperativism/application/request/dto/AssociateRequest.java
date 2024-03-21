package br.com.southsystem.cooperativism.application.request.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AssociateRequest {
	
	@Size(min = 3, max = 30, message = "Nome deve conter entre 3 e 30 caracteres")
	@NotBlank(message = "Nome deve conter entre 3 e 30 caracteres")
	@Pattern(regexp = "^[A-Za-zÀ-ú ]*$", message = "Nome deve conter apenas letras")
	private String name;
	
	@Size(min = 11, max = 11, message = "CPF deve conter 11 dígitos")
    @NotBlank(message = "CPF não deve ser nulo")
    @CPF(message = "Insira um CPF válido")
	private String cpf;

	public AssociateRequest() {
		super();
	}

	public AssociateRequest(String name, String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "AssociateRequest [name=" + name + ", cpf=" + cpf + "]";
	}

}
