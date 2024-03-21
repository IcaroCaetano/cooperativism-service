package br.com.southsystem.cooperativism.infrastructure.mappers;

import java.time.LocalDateTime;

import br.com.southsystem.cooperativism.application.request.dto.AgendaRequest;
import br.com.southsystem.cooperativism.domain.model.AgendaModel;

public class AgendaMapper {
	
	private AgendaMapper() {
		super();
	}

	public static AgendaModel requestToModel(AgendaRequest request) {
		AgendaModel model = new AgendaModel();
		model.setCode(request.getCode());
		model.setDescription(request.getDescription());
		model.setDateRegister(LocalDateTime.now());
		return model;
	}

}
