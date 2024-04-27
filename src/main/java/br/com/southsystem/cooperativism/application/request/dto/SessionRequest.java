package br.com.southsystem.cooperativism.application.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Responsável pela Sessão")
public class SessionRequest {
	
	@NotNull
	@Schema(description = "Entrada de dados para inclusão do código da pauta ", defaultValue="1")
	private Integer code;
	
	@Schema(description = "Entrada de dados para inclusão do tempo da sessão em minutos.", defaultValue="10")
	private Integer sessionTime;

	private SessionRequest() {
		super();
	}

	private SessionRequest(@NotBlank Integer code, Integer sessionTime) {
		super();
		this.code = code;
		this.sessionTime = sessionTime;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Integer sessionTime) {
		this.sessionTime = sessionTime;
	}

	@Override
	public String toString() {
		return "SessionRequest [code=" + code + ", sessionTime=" + sessionTime + "]";
	}
	
}