package br.com.southsystem.cooperativism.application.response.dto;

public class VoteCountResponse {
	
	private Integer code;
	
	private Long positiveVotes;
	
	private Long negativeVotes;
	
	public VoteCountResponse() {
		super();
	}
	
	public VoteCountResponse(Integer code, Long positiveVotes, Long negativeVotes) {
		super();
		this.code = code;
		this.positiveVotes = positiveVotes;
		this.negativeVotes = negativeVotes;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Long getPositiveVotes() {
		return positiveVotes;
	}

	public void setPositiveVotes(Long positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	public Long getNegativeVotes() {
		return negativeVotes;
	}

	public void setNegativeVotes(Long negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

}
