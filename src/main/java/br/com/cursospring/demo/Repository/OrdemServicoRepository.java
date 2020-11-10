package br.com.cursospring.demo.Repository;

import br.com.cursospring.demo.Model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository <OrdemServico, Long>{



}
