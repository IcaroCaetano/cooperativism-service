package br.com.southsystem.cooperativism.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class SessionModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 
	
	@JsonIgnore
	private LocalDateTime sessionDateInitial;
	
	@Column(name = "session_time")
	private Integer sessionTime;
	
	@ManyToOne
	@JoinColumn(name = "agenda_id")
	private AgendaModel agenda;

	public SessionModel() {
		super();
	}

	public SessionModel(UUID id, LocalDateTime sessionDateInitial, Integer sessionTime, AgendaModel agenda) {
		super();
		this.id = id;
		this.sessionDateInitial = sessionDateInitial;
		this.sessionTime = sessionTime;
		this.agenda = agenda;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getSessionDateInitial() {
		return sessionDateInitial;
	}

	public void setSessionDateInitial(LocalDateTime sessionDateInitial) {
		this.sessionDateInitial = sessionDateInitial;
	}

	public Integer getSessionTime() {
		return sessionTime;
	}

	public void setSessionTime(Integer sessionTime) {
		this.sessionTime = sessionTime;
	}

	public AgendaModel getAgenda() {
		return agenda;
	}

	public void setAgenda(AgendaModel agenda) {
		this.agenda = agenda;
	}

	@Override
	public String toString() {
		return "SessionModel [id=" + id + ", sessionDateInitial=" + sessionDateInitial + ", sessionTime=" + sessionTime
				+ ", agenda=" + agenda + "]";
	}

}
