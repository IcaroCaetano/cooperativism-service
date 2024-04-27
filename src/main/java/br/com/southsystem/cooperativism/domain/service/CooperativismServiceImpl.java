package br.com.southsystem.cooperativism.domain.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.southsystem.cooperativism.application.request.dto.AgendaRequest;
import br.com.southsystem.cooperativism.application.request.dto.AssociateRequest;
import br.com.southsystem.cooperativism.application.request.dto.SessionRequest;
import br.com.southsystem.cooperativism.application.request.dto.VotingManagementRequest;
import br.com.southsystem.cooperativism.application.response.dto.MessageResponse;
import br.com.southsystem.cooperativism.application.response.dto.VoteCountResponse;
import br.com.southsystem.cooperativism.common.exception.CooperativismBusinessException;
import br.com.southsystem.cooperativism.domain.model.AgendaModel;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;
import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;
import br.com.southsystem.cooperativism.domain.port.incoming.CooperativismUseCase;
import br.com.southsystem.cooperativism.domain.port.outcoming.PersistCooperativismPort;
import br.com.southsystem.cooperativism.domain.port.outcoming.RetrieveCooperativismPort;
import br.com.southsystem.cooperativism.infrastructure.exception.error.DocumentErrors;
import br.com.southsystem.cooperativism.infrastructure.mappers.AgendaMapper;
import br.com.southsystem.cooperativism.infrastructure.mappers.AssociateMapper;
import br.com.southsystem.cooperativism.infrastructure.mappers.SessionMapper;
import br.com.southsystem.cooperativism.infrastructure.mappers.VotingManagementMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class CooperativismServiceImpl implements CooperativismUseCase {
	
	static final Logger logger = LoggerFactory.getLogger(CooperativismServiceImpl.class);
	
	@Autowired
	private PersistCooperativismPort persistPort;
	
	@Autowired
	private RetrieveCooperativismPort retrievePort;

	@Override
	public ResponseEntity<Object> createAssossiate(@Valid AssociateRequest request) {
		try {
			
			Optional<AssociateModel>  associate =  this.retrievePort.getAssociateByCpf(request.getCpf());
			
			if (associate.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new MessageResponse("O associado de cpf " + request.getCpf() + " já foi cadastrado."));
			}
			
			AssociateModel model = this.persistPort.insertAssociate(AssociateMapper.requestToModel(request));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(model);
		} catch (Exception e) {
			logger.error("Ocorreu algum erro ao criar o Associado: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Ocorreu algum erro ao criar o Associado"));
		}
	}

	@Override
	public ResponseEntity<Object> getAllAssociates() {
		List <AssociateModel> listAssociates =  this.retrievePort.getAllAssociates();
		
		if (!listAssociates.isEmpty()) {
			logger.info("Listando {} associado(s).", listAssociates.size());
			return ResponseEntity.status(HttpStatus.OK).body(listAssociates);
		} else { 
			logger.info("Não há nennum associado cadastrado");
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Não há nennum associado cadastrado."));
		}
	}

	@Override
	public ResponseEntity<Object> createAgenda(@Valid AgendaRequest request) {
		try {
			
			Optional<AgendaModel> agenda = this.retrievePort.getAgendaByCode(request.getCode());
			
			if (agenda.isPresent()) {
				return ResponseEntity.status(HttpStatus.CONFLICT)
						.body(new MessageResponse("A Pauta de código " + request.getCode() + " já foi cadastrada."));
			}
			
			AgendaModel model =  this.persistPort.insertAgenda(AgendaMapper.requestToModel(request));
		return ResponseEntity.status(HttpStatus.CREATED).body(model);
		} catch (Exception e) {
			logger.error("Ocorreu algum erro ao criar o Associado: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Ocorreu algum erro ao criar a Pauta"));
		}
	}

	@Override
	public ResponseEntity<Object> getAllAgendas() {
		List <AgendaModel> listAgendas =  this.retrievePort.getAllAgendas();
		
		if (!listAgendas.isEmpty()) {
			logger.info("Listando {} pauta(s).", listAgendas.size());
			return ResponseEntity.status(HttpStatus.OK).body(listAgendas);
		} else { 
			logger.info("Não há nennuma Pauta cadastrada");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Não há nennuma Pauta cadastrada."));
		}
	}

	@Override
	public ResponseEntity<Object> createSession(@Valid SessionRequest request) {
		
		Optional<AgendaModel> agenda = this.retrievePort.getAgendaByCode(request.getCode());
			
		if (!agenda.isPresent())
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422001);
		
		try {	
			
			SessionModel model = this.persistPort.insertSession(SessionMapper.requestToModel(request, agenda.get()));
			return ResponseEntity.status(HttpStatus.CREATED).body(model);
			
		} catch (Exception e) {
			logger.error("Ocorreu algum erro ao abrir a Sessão: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Ocorreu algum erro ao abrir a Sessão"));
		}
	}
	
	@Override
	public ResponseEntity<Object> getSessionByAgenda(@Valid Integer code) {

		Optional <SessionModel> session =  this.retrievePort.getSessionByAgendaCode(code);
		
		if (!session.isPresent())
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422002);
		
		try {
	
			return ResponseEntity.status(HttpStatus.OK).body(session);
		
		} catch (Exception e) {
			logger.error("Ocorreu algum erro ao obter a Sessão: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Ocorreu algum erro ao obter a Sessão"));
		}
	
	}

	@Override
	public ResponseEntity<Object> makeVote(@Valid VotingManagementRequest request) {
				
		Optional<AssociateModel> associateModel = this.retrievePort.getAssociateByCpf(request.getCpf());
		
		if (!associateModel.isPresent())
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422003);
		
		Optional<SessionModel> sessionModel = this.retrievePort.getSessionByID(UUID.fromString(request.getSessionId()));
		
		if (!sessionModel.isPresent())
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422004);		
		
		Long differenceInMinutes = sessionModel.get().getSessionDateInitial().until(LocalDateTime.now(), ChronoUnit.MINUTES);
		
		if (differenceInMinutes > sessionModel.get().getSessionTime()) {
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422005);
		}
		
		Optional<VotingManagementModel> votingModel =  this.retrievePort.getVotingByCpf(request.getCpf(), sessionModel.get().getAgenda().getCode());
		
		if (votingModel.isPresent()) 
			throw new CooperativismBusinessException(DocumentErrors.ERRO_422006);
		
		try {	
			
			VotingManagementModel model = this.persistPort.insertVote(VotingManagementMapper.requestToModel(request, associateModel.get(), sessionModel.get()));
			return ResponseEntity.status(HttpStatus.CREATED).body(model);
		
		} catch (Exception e) {
			logger.error("Ocorreu algum erro ao efetuar a votação: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new MessageResponse("Ocorreu algum erro ao efetuar a votação"));
		}
	}

	@Override
	public ResponseEntity<Object> getCountVote(@Valid Integer code) {
		VoteCountResponse reponse = VotingManagementMapper.modelToResponse(this.retrievePort.getVoteCount(code));
		return ResponseEntity.status(HttpStatus.OK).body(reponse);
	}

}