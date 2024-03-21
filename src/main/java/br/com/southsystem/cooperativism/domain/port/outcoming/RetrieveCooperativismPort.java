package br.com.southsystem.cooperativism.domain.port.outcoming;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.southsystem.cooperativism.domain.model.AgendaModel;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;
import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;

public interface RetrieveCooperativismPort {
	
	public List<AssociateModel> getAllAssociates();
	
	public List<AgendaModel> getAllAgendas();
	
	public Optional<AgendaModel> getAgendaByID(UUID id);
	
	public Optional<AssociateModel> getAssociateByCpf(String cpf);
	
	public Optional<SessionModel> getSessionByID(UUID id);
	
	public Optional<SessionModel> getSessionByAgendaCode(Integer code);
	
	public Optional<VotingManagementModel> getVotingByCpf(String cpf, Integer code);
	
	public List<Object[]> getVoteCount(Integer code);

}
