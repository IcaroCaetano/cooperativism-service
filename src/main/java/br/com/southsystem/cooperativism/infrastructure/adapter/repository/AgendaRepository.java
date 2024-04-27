package br.com.southsystem.cooperativism.infrastructure.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.southsystem.cooperativism.domain.model.AgendaModel;

public interface AgendaRepository extends JpaRepository<AgendaModel, UUID>{
	
	@Query("SELECT am FROM AgendaModel am WHERE am.code = :code")
	Optional<AgendaModel> findByCode(@Param("code") Integer code);

}
