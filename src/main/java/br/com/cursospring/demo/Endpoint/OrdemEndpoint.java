package br.com.cursospring.demo.Endpoint;

import br.com.cursospring.demo.Error.CustomErrorType;
import br.com.cursospring.demo.Model.Carro;
import br.com.cursospring.demo.Model.Motorista;
import br.com.cursospring.demo.Model.OrdemServico;
import br.com.cursospring.demo.Model.StatusOrdemServico;
import br.com.cursospring.demo.Repository.CarroRepository;
import br.com.cursospring.demo.Repository.MotoristaRepository;
import br.com.cursospring.demo.Repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/ordens")
public class OrdemEndpoint {

    @Autowired
    private final OrdemServicoRepository ordemDAO;

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CarroRepository carroRepository;


    public OrdemEndpoint(OrdemServicoRepository ordemDAO) {
        this.ordemDAO = ordemDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(ordemDAO.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getOrdemById(@PathVariable("id")Long id){
        Optional<OrdemServico> ordemServico = ordemDAO.findById(id);
        if (ordemServico == null)
            return new ResponseEntity<>(new CustomErrorType("Ordem Not Found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ordemServico, HttpStatus.OK);
    }

    @PostMapping

    public ResponseEntity<?> save(@RequestBody OrdemServico ordemServico){
        Motorista motorista = (Motorista) motoristaRepository.findAllById(ordemServico.getMotorista().getId());

        Carro carro = carroRepository.findAllById(ordemServico.getCarro().getId());



        ordemServico.setCarro(carro);
        ordemServico.setMotorista(motorista);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setAbertura(LocalDateTime.now());
        return new ResponseEntity<>(ordemDAO.save(ordemServico), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        ordemDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrdemServico ordemServico){
        ordemDAO.save(ordemServico);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setAbertura(LocalDateTime.now());
        return new ResponseEntity<>(ordemDAO.save(ordemServico) , HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> finalizar(@RequestBody OrdemServico ordemServico){


        ordemDAO.save(ordemServico);
        ordemServico.setStatus(StatusOrdemServico.FINALIZADA);

        ordemServico.setAbertura(LocalDateTime.now());
        return new ResponseEntity<>(ordemDAO.save(ordemServico) , HttpStatus.OK);
    }
}
