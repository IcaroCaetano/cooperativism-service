package br.com.southsystem.cooperativism.infrastructure.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.southsystem.cooperativism.domain.model.AssociateModel;

public interface AssociateRepository extends JpaRepository<AssociateModel, UUID> { 
	
	@Query("SELECT am FROM AssociateModel am WHERE am.cpf = :cpf")
	Optional<AssociateModel> findByCpf(@Param("cpf") String cpf);

}
