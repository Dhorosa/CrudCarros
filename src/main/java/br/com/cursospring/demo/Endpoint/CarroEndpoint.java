package br.com.cursospring.demo.Endpoint;

import br.com.cursospring.demo.Error.CustomErrorType;
import br.com.cursospring.demo.Model.Carro;
import br.com.cursospring.demo.Repository.CarroRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroEndpoint {

    private final CarroRepository carroDAO;

    public CarroEndpoint(CarroRepository carroDAO) {
        this.carroDAO = carroDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(){
        return new ResponseEntity<>(carroDAO.findAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getCarroById(@PathVariable("id")Long id){
        Optional<Carro> carro = carroDAO.findById(id);
        if (carro == null)
            return new ResponseEntity<>(new CustomErrorType("Carro Not Found"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(carro, HttpStatus.OK);
    }
    @GetMapping(path = "/findByMarca/{marca}")
    public ResponseEntity<?> findCarroByMarca(@PathVariable String marca){

        return new ResponseEntity<>(carroDAO.findByMarcaIgnoreCaseContaining(marca), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Carro carro){
        return new ResponseEntity<>(carroDAO.save(carro), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        carroDAO.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Carro carro){
       carroDAO.save(carro);
        return new ResponseEntity<>(carroDAO.save(carro) , HttpStatus.OK);
    }

}
