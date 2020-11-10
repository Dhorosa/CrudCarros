package br.com.cursospring.demo.Endpoint;

import br.com.cursospring.demo.Error.CustomErrorType;
import br.com.cursospring.demo.Model.Motorista;
import br.com.cursospring.demo.Repository.MotoristaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/motoristas")
public class MotoristaEndpoint {

    private final MotoristaRepository motoristaDAO;

    public MotoristaEndpoint(MotoristaRepository motoristaDAO) {
        this.motoristaDAO = motoristaDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(motoristaDAO.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getMotoristaById(@PathVariable("id")Long id){
        Optional<Motorista> motorista = motoristaDAO.findById(id);
        if (motorista == null)
            return new ResponseEntity<>(new CustomErrorType("Motorista Not Found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(motorista, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Motorista motorista){
        return new ResponseEntity<>(motoristaDAO.save(motorista), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        motoristaDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Motorista motorista){
        motoristaDAO.save(motorista);
        return new ResponseEntity<>(motoristaDAO.save(motorista) , HttpStatus.OK);
    }
}
