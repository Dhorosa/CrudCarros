package br.com.cursospring.demo.Repository;

import br.com.cursospring.demo.Model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, Long> {


         Motorista findAllById(Long id);
}
