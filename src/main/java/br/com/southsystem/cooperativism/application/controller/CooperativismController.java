package br.com.southsystem.cooperativism.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.southsystem.cooperativism.application.request.dto.AgendaRequest;
import br.com.southsystem.cooperativism.application.request.dto.AssociateRequest;
import br.com.southsystem.cooperativism.application.request.dto.SessionRequest;
import br.com.southsystem.cooperativism.application.request.dto.VotingManagementRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

public interface CooperativismController {
	
	@Operation(summary = "Cadastrar um Associado", description = "Criar um novo Associado.", tags = {"Associado" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Associado criado com Sucesso.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "422", description = "Erro ao cadastrar o Associado.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
			@Content(mediaType = "application/json") }) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@Valid @RequestBody AssociateRequest request);
	

	@Operation(summary = "Lista todos Associados", description = "Listar todos os Assocciados", tags = { "Associado" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = 
			@Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "422", description = "Erro ao obter os Associados.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content =
			@Content) })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllAssociates();
	
	@Operation(summary = "Cadastrar uma Pauta", description = "Criar um nova Pauta.", tags = {"Pauta" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pauta criada com Sucesso.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "422", description = "Erro ao cadastrar o Pauta.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
			@Content(mediaType = "application/json") }) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@Valid @RequestBody AgendaRequest request);
	
	@Operation(summary = "Lista todos as Pautas", description = "Listar todos as Pautas", tags = { "Pauta" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = 
			@Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "422", description = "Erro ao obter as Pautas.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content =
			@Content) })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getAllAgendas();
	
	@Operation(summary = "Abrir uma Sessão", description = "Abrir uma nova Sessão.", tags = {"Sessão" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Associado criado com Sucesso.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "422", description = "Erro ao Abrir uma Sessão", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
			@Content(mediaType = "application/json") }) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> create(@Valid @RequestBody SessionRequest request);
	
	@Operation(summary = "Obter a última sessao", description = "Obter a última sessao aberta pelo código da Pauta", tags = { "Sessão" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = 
			@Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "422", description = "Erro ao obter ao obter a última sessão.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content =
			@Content) })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getSessionByAgenda(@Valid @PathParam(value = "code") Integer code);
	
	
	@Operation(summary = "Efetuar um voto", description = "Gestão de Votação na Pauta.", tags = {"Votação" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Voto efetuado com Sucesso.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "422", description = "Erro ao efetuar o voto.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
			@Content(mediaType = "application/json") }) })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Object> makeVote(@Valid @RequestBody VotingManagementRequest request);
	
	@Operation(summary = "Obter a contagem dos votos", description = "Obter a contagem dos votos por Pauta", tags = { "Votação" })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation", content = 
			@Content(schema = @Schema(implementation = ResponseEntity.class))),
			@ApiResponse(responseCode = "422", description = "Erro ao obter a contagem dos votos.", content = {
			@Content(mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", description = "Internal server error", content =
			@Content) })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> getCountVote(@Valid @PathParam(value = "code") Integer code);

}
