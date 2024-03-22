package br.com.southsystem.cooperativism.application.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Responsável pelo Pauta")
public class AgendaRequest {
	
	@Schema(description = "Entrada de dados para inclusão do código da associado. O código não pode ser duplicado.", defaultValue="1")
	@Nonnull
	private Integer code;
	
	@Schema(description = "Entrada de dados para inclusão da descrição da Pauta.", defaultValue="PAUTA 001 de Implantação de Sistema de gestão")
	@Size(min = 2, max = 50, message = "Nome deve conter entre 2 e 50 caracteres")
	@NotBlank
	private String description;
	
	public AgendaRequest() {
		super();
	}

	public AgendaRequest(Integer code, @Size(min = 2, max = 50, message = "Nome deve conter entre 2 e 50 caracteres") @NotBlank String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AgendaRequest [code=" + code + ", description=" + description + "]";
	}
	
}