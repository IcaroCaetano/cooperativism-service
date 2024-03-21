package br.com.southsystem.cooperativism.infrastructure.adapter.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.southsystem.cooperativism.domain.model.VotingManagementModel;

public interface VotingManagementRepository extends JpaRepository<VotingManagementModel, UUID> {
	
	
	@Query("SELECT vm FROM VotingManagementModel vm INNER JOIN vm.associate a INNER JOIN vm.session s WHERE a.cpf = :cpf AND  vm.session.agenda.code = :code")
	public Optional<VotingManagementModel> findVotingByCpf(@Param("cpf") String cpf, @Param("code") Integer code);
	
	@Query(nativeQuery = true, value = 
			 " SELECT DISTINCT  "
			 +"       ag.code, "
			 + "      (select count(vmi.*) FROM public.voting_management vmi "
			 + "       INNER JOIN public.session smi on (vmi.session_id = smi.id) "
			 + "       INNER JOIN public.agenda agi on (smi.agenda_id = agi.id) "
			 + "       WHERE agi.code = :code and vmi.vote = 'YES') as positiveVotes , "
			 + "      (select count(vmi.*) FROM public.voting_management vmi "
			 + "       INNER JOIN public.session smi on (vmi.session_id = smi.id) "
			 + "       INNER JOIN public.agenda agi on (smi.agenda_id = agi.id) "
			 + "       WHERE agi.code = :code and vmi.vote = 'NO') as  negativeVotes "
			 + "FROM public.voting_management vm "
			 + "INNER JOIN public.session sm on (vm.session_id = sm.id) "
			 + "INNER JOIN public.agenda ag on (sm.agenda_id = AG.id) "
			 + "WHERE ag.code = :code ")
	public List<Object[]> getVoteCount(Integer code);

}
