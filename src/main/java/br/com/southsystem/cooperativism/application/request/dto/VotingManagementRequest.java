package br.com.southsystem.cooperativism.application.request.dto;

public class VotingManagementRequest {
	
	private String cpf;
	
	private String sessionId;
	
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
