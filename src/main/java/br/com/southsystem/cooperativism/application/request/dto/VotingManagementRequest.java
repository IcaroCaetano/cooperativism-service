package br.com.southsystem.cooperativism.application.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Responsável pelo Gerenciamento dos Votos.")
public class VotingManagementRequest {
	
	@Schema(description = "Entrada de dados para inclusão do cpf o Associado ", defaultValue="46735825004")
	private String cpf;
	
	@Schema(description = "Entrada de dados para inclusão do ID da Sessão ", defaultValue="UUID da Sessão Ex: b7a0fa14-4b9c-4996-afcd-b662ed4ad2e9")
	private String sessionId;
	
	@Schema(description = "Entrada de dados para escolha de votação ", defaultValue="Digitar YES ou NO")
	private String vote;

	public VotingManagementRequest() {
		super();
	}

	public VotingManagementRequest(String cpf, String sessionId, String vote) {
		super();
		this.cpf = cpf;
		this.sessionId = sessionId;
		this.vote = vote;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}

	@Override
	public String toString() {
		return "VotingManagementRequest [cpf=" + cpf + ", sessionId=" + sessionId + ", vote=" + vote + "]";
	}

}
