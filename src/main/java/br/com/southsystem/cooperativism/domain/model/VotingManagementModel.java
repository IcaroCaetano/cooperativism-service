package br.com.southsystem.cooperativism.domain.model;

import java.util.UUID;

import br.com.southsystem.cooperativism.common.enums.YesOrNoEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "voting_management")
public class VotingManagementModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 
	
	@Column(length = 3)
	@Enumerated(EnumType.STRING)
	private YesOrNoEnum vote;
	
	@ManyToOne
	@JoinColumn(name = "session_id")
	private SessionModel session;
	
	@ManyToOne
	@JoinColumn(name = "associate_id")
	private AssociateModel associate;
	
	public VotingManagementModel() {
		super();
	}

	public VotingManagementModel(UUID id, YesOrNoEnum vote, SessionModel session, AssociateModel associate) {
		super();
		this.id = id;
		this.vote = vote;
		this.session = session;
		this.associate = associate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public YesOrNoEnum getVote() {
		return vote;
	}

	public void setVote(YesOrNoEnum vote) {
		this.vote = vote;
	}

	public SessionModel getSession() {
		return session;
	}

	public void setSession(SessionModel session) {
		this.session = session;
	}

	public AssociateModel getAssociate() {
		return associate;
	}

	public void setAssociate(AssociateModel associate) {
		this.associate = associate;
	}

	@Override
	public String toString() {
		return "VotingManagementModel [id=" + id + ", vote=" + vote + ", session=" + session + ", associate="
				+ associate + "]";
	}	

}
