package org.generation.ondetemrole.repository;

import java.util.List;

import org.generation.ondetemrole.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	public List<Postagem> findAllByTipoTurismoContainingIgnoreCase(String tipoTurismo);

	public List<Postagem> findAllByTextoContainingIgnoreCase(String texto);

}
