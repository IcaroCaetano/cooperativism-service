package br.com.southsystem.cooperativism.infrastructure.mappers;

import java.time.LocalDateTime;

import br.com.southsystem.cooperativism.application.request.dto.SessionRequest;
import br.com.southsystem.cooperativism.domain.model.AgendaModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;

public class SessionMapper {
	
	private static final Integer SESSION_TIME_MINUTE_DEFAULT = 1;
	
	private SessionMapper() {
		super();
	}

	public static SessionModel requestToModel(SessionRequest request, AgendaModel agendaModel) {
		SessionModel model = new SessionModel();
		model.setSessionTime(request.getSessionTime() != 0 ? request.getSessionTime() : SESSION_TIME_MINUTE_DEFAULT);
		model.setSessionDateInitial(LocalDateTime.now());
		model.setAgenda(agendaModel);
		return model;
	}

}
