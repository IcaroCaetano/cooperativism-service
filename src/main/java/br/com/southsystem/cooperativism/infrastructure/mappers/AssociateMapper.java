package br.com.southsystem.cooperativism.infrastructure.mappers;

import br.com.southsystem.cooperativism.application.request.dto.AssociateRequest;
import br.com.southsystem.cooperativism.domain.model.AssociateModel;

public class AssociateMapper {

	private AssociateMapper() {
		super();
	}
	
	public static AssociateModel requestToModel(AssociateRequest request) {
		AssociateModel model = new AssociateModel();
		model.setName(request.getName());
		model.setCpf(request.getCpf());
		return model;
	}

}
