package br.com.southsystem.cooperativism.infrastructure.mappers;

import java.util.List;

import br.com.southsystem.cooperativism.application.request.dto.VotingManagementRequest;
import br.com.southsystem.cooperativism.application.response.dto.VoteCountResponse;
import br.com.southsystem.cooperativism.common.enums.YesOrNoEnum;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;
import br.com.southsystem.cooperativism.domain.model.SessionModel;
import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;

public class VotingManagementMapper {
	
	private VotingManagementMapper() {
		super();
	}

	public static VotingManagementModel requestToModel(VotingManagementRequest request, AssociateModel associate, SessionModel session) {
		VotingManagementModel model = new VotingManagementModel();
		model.setAssociate(associate);
		model.setSession(session);
		model.setVote(YesOrNoEnum.valueOf(request.getVote()));
		return model;
	}
	
	public static VoteCountResponse modelToResponse(List<Object[]> object) {
		
		VoteCountResponse response = new VoteCountResponse();
		for (Object[] result : object) {
			response.setCode((Integer) result[0]);
			response.setPositiveVotes((Long) result[1]);
			response.setNegativeVotes((Long) result[2]);
		}
		
		return response;
	}

}
