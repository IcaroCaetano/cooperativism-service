package br.com.southsystem.cooperativism.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "agenda", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class AgendaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id; 
	
	@Column(nullable = false)
	private Integer code;

	@Column(nullable = false)
	private String description; 
	
	@JsonIgnore
	@Column(nullable = false)
    private LocalDateTime dateRegister;

	public AgendaModel() {
		super();
	}

	public AgendaModel(UUID id, Integer code, String description, LocalDateTime dateRegister) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.dateRegister = dateRegister;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public LocalDateTime getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(LocalDateTime dateRegister) {
		this.dateRegister = dateRegister;
	}

	@Override
	public String toString() {
		return "AgendaModel [id=" + id + ", code=" + code + ", description=" + description + ", dateRegister="
				+ dateRegister + "]";
	}

}
