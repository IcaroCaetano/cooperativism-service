package br.com.southsystem.cooperativism.infrastructure.adapter.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.southsystem.cooperativism.domain.model.AgendaModel;

public interface AgendaRepository extends JpaRepository<AgendaModel, UUID>{

}
