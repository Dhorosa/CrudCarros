package br.com.cursospring.demo.Repository;

import br.com.cursospring.demo.Model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByMarcaIgnoreCaseContaining(String marca);


    Carro findAllById(Long id);
}
