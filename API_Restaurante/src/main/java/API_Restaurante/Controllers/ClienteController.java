package API_Restaurante.Controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import API_Restaurante.Models.Cliente;
import API_Restaurante.Services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClientes(Pageable pageable){

        Page<Cliente> result = clienteService.getAllClientes(pageable);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Page<Cliente>> findByName(@PathVariable String name, Pageable pageable){

        Page<Cliente> result = clienteService.findByName(name, pageable);

        return new ResponseEntity<>(result,HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Cliente> addCliente(@RequestBody Cliente cliente){

        Cliente result = clienteService.addCliente(cliente);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteCliente(@PathVariable UUID id){

        return clienteService.deleteCliente(id);
    }
}
