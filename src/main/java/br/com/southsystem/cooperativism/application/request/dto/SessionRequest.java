package br.com.southsystem.cooperativism.application.request.dto;

import jakarta.validation.constraints.NotBlank;

public class SessionRequest {
	
	@NotBlank
	private String agendaId;
	
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
