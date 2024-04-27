package br.com.southsystem.cooperativism.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.southsystem.cooperativism.domain.model.AgendaModel;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;
import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;
import br.com.southsystem.cooperativism.domain.port.outcoming.PersistCooperativismPort;
import br.com.southsystem.cooperativism.domain.port.outcoming.RetrieveCooperativismPort;
import br.com.southsystem.cooperativism.infrastructure.adapter.repository.AgendaRepository;
import br.com.southsystem.cooperativism.infrastructure.adapter.repository.AssociateRepository;
import br.com.southsystem.cooperativism.infrastructure.adapter.repository.SessionRepository;
import br.com.southsystem.cooperativism.infrastructure.adapter.repository.VotingManagementRepository;

@Service
public class CooperativismServiceAdapter implements PersistCooperativismPort, RetrieveCooperativismPort {
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Autowired
	private VotingManagementRepository votingManagementRepository;

	@Override
	public AssociateModel insertAssociate(AssociateModel associate) {
		return this.associateRepository.save(associate);
	}

	@Override
	public List<AssociateModel> getAllAssociates() {
		return this.associateRepository.findAll();
	}

	@Override
	public AgendaModel insertAgenda(AgendaModel agenda) {
		return this.agendaRepository.save(agenda);
	}

	@Override
	public List<AgendaModel> getAllAgendas() {
		return this.agendaRepository.findAll();
	}

	@Override
	public SessionModel insertSession(SessionModel session) {
		return this.sessionRepository.save(session);
	}

	@Override
	public Optional<AgendaModel> getAgendaByID(UUID id) {
		return this.agendaRepository.findById(id);
	}

	@Override
	public Optional<AssociateModel> getAssociateByCpf(String cpf) {
		return this.associateRepository.findByCpf(cpf);
	}

	@Override
	public Optional<SessionModel> getSessionByID(UUID id) {
		return this.sessionRepository.findById(id);
	}
	
	@Override
	public Optional<SessionModel> getSessionByAgendaCode(Integer code) {
		return this.sessionRepository.getSessionByAgendaCode(code);
	}

	@Override
	public VotingManagementModel insertVote(VotingManagementModel vote) {
		return this.votingManagementRepository.save(vote);
	}
	
	@Override
	public Optional<VotingManagementModel> getVotingByCpf(String cpf, Integer code) {
		return this.votingManagementRepository.findVotingByCpf(cpf, code);
	}

	@Override
	public List<Object[]> getVoteCount(Integer code) {
		return this.votingManagementRepository.getVoteCount(code);
	}

	@Override
	public Optional<AgendaModel> getAgendaByCode(Integer code) {
		return this.agendaRepository.findByCode(code);
	}

}
