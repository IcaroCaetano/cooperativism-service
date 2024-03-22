package br.com.southsystem.cooperativism.application.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Responsável pela Sessão")
public class SessionRequest {
	
	@NotBlank
	@Schema(description = "Entrada de dados para inclusão do cpf do Associado ", defaultValue="46735825004")
	private String agendaId;
	
	@Schema(description = "Entrada de dados para inclusão do tempo da sessão em minutos.", defaultValue="10")
	private Integer sessionTime;

	private SessionRequest() {
		super();
	}

	private SessionRequest(@NotBlank String agendaId, Integer sessionTime) {
		super();
		this.agendaId = agendaId;
		this.sessionTime = sessionTime;
	}

	public String getAgendaId() {
		return agendaId;
	}

	public void setAgendaId(String agendaId) {
		this.agendaId = agendaId;
	}

	public Integer getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Integer sessionTime) {
		this.sessionTime = sessionTime;
	}

	@Override
	public String toString() {
		return "SessionRequest [agendaId=" + agendaId + ", sessionTime=" + sessionTime + "]";
	}
	
}