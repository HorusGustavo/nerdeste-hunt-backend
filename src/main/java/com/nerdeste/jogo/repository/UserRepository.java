package com.nerdeste.jogo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nerdeste.jogo.entity.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	Optional<User>findByUserName(String userName);
	boolean existsByUserName(String userName);
	
	// Para o ranking Top 10
    List<User> findTop10ByDataConclusaoJogoIsNotNullOrderByTempoTotalSegundosAsc();
    
    // Para buscar todos que completaram
    List<User> findByDataConclusaoJogoIsNotNullOrderByTempoTotalSegundosAsc();

	
}

