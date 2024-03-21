package br.com.southsystem.cooperativism.domain.port.incoming;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.southsystem.cooperativism.application.request.dto.AgendaRequest;
import br.com.southsystem.cooperativism.application.request.dto.AssociateRequest;
import br.com.southsystem.cooperativism.application.request.dto.SessionRequest;
import br.com.southsystem.cooperativism.application.request.dto.VotingManagementRequest;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

public interface CooperativismUseCase {
	
	public ResponseEntity<Object> createAssossiate(@Valid @RequestBody AssociateRequest resquest);
	
	public ResponseEntity<Object> getAllAssociates();
		
	public ResponseEntity<Object> createAgenda(@Valid @RequestBody AgendaRequest resquest);
	
	public ResponseEntity<Object> getAllAgendas();
	
	public ResponseEntity<Object> createSession(@Valid @RequestBody SessionRequest resquest);
	
	public ResponseEntity<Object> makeVote(@Valid @RequestBody VotingManagementRequest request);
	
	public ResponseEntity<Object> getSessionByAgenda(@Valid @PathParam(value = "code") Integer code);
	
	public ResponseEntity<Object> getCountVote(@Valid @PathParam(value = "code") Integer code);

}
