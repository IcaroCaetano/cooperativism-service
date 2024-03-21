package br.com.southsystem.cooperativism.infrastructure.adapter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.southsystem.cooperativism.domain.model.SessionModel;

public interface SessionRepository extends JpaRepository<SessionModel, UUID>{
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM "
			+ " (SELECT s.* FROM session s  INNER JOIN agenda a ON (s.agenda_id = a.id)  WHERE a.code = :code "
			+ "  ORDER BY s.session_date_initial desc) table1 "
			+ "  LIMIT 1")
	Optional<SessionModel> getSessionByAgendaCode(@Param("code") Integer code);

}
