package br.com.southsystem.cooperativism.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.southsystem.cooperativism.application.request.dto.AgendaRequest;
import br.com.southsystem.cooperativism.application.request.dto.AssociateRequest;
import br.com.southsystem.cooperativism.application.request.dto.SessionRequest;
import br.com.southsystem.cooperativism.application.request.dto.VotingManagementRequest;
import br.com.southsystem.cooperativism.domain.port.incoming.CooperativismUseCase;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/cooperativism")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CooperativismControllerImpl implements CooperativismController {
	
	private final Logger logger = LoggerFactory.getLogger(CooperativismControllerImpl.class);
	
	private CooperativismUseCase useCase;
	
	public CooperativismControllerImpl(CooperativismUseCase cooperativismUseCase) {
		this.useCase = cooperativismUseCase;
	}

	@Override
	@PostMapping("/createAssociate")
	public ResponseEntity<Object> create(@Valid AssociateRequest request) {
		logger.info("Cadastrando Associado com o cpf: {}", request.getCpf());
		return this.useCase.createAssossiate(request);
	}

	@Override
	@GetMapping("/listAllAssociates")
	public ResponseEntity<Object> getAllAssociates() {
		logger.info("Listar todos os Associados");
		return this.useCase.getAllAssociates();
	}

	@Override
	@PostMapping("/createAgenda")
	public ResponseEntity<Object> create(@Valid AgendaRequest request) {
		logger.info("Cadastrando Pauta com o código: {}", request.getCode());
		return this.useCase.createAgenda(request);
	}

	@Override
	@GetMapping("/listAllAgendas")
	public ResponseEntity<Object> getAllAgendas() {
		logger.info("Listar todas as Pautas");
		return this.useCase.getAllAgendas();
	}

	@Override
	@PostMapping("/openSession")
	public ResponseEntity<Object> create(@Valid SessionRequest request) {
		logger.info("Abrir uma sessão");
		return this.useCase.createSession(request);
	}
	
	@Override
	@GetMapping("/listSession/{code}")
	public ResponseEntity<Object> getSessionByAgenda(@Valid @PathParam(value = "code") Integer code) {
		logger.info("Listar todos os sessoes pela Pauta");
		return this.useCase.getSessionByAgenda(code);
	}
	

	@PostMapping("/makeVote")
	public ResponseEntity<Object> makeVote(@Valid VotingManagementRequest request) {
		logger.info("Efetuar uma votação");
		return this.useCase.makeVote(request);
	}

	@Override
	@GetMapping("/countVote/{code}")
	public ResponseEntity<Object> getCountVote(@Valid Integer code) {
		logger.info("Obter resultado da votação");
		return this.useCase.getCountVote(code);
	}

}
