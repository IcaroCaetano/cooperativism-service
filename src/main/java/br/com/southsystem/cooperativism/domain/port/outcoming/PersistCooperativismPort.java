package br.com.southsystem.cooperativism.domain.port.outcoming;

import br.com.southsystem.cooperativism.domain.model.AgendaModel;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;
import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;

public interface PersistCooperativismPort {
	
	 public AssociateModel insertAssociate(AssociateModel associate);
	 
	 public AgendaModel insertAgenda(AgendaModel agenda);
	 
	 public SessionModel insertSession(SessionModel session);
	 
	 public VotingManagementModel insertVote(VotingManagementModel vote);

}
