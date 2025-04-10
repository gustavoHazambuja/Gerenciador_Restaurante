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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API_Restaurante.Enums.EnumPrato;
import API_Restaurante.Models.Prato;
import API_Restaurante.Services.PratoService;

@RestController
@RequestMapping(value = "/cardapio")
public class PratoController {
    
    @Autowired
    private PratoService pratoService;

    @GetMapping
    public ResponseEntity<Page<Prato>> getPratos(Pageable pageable){

        Page<Prato> result = pratoService.getPratos(pageable);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Page<Prato>> findByName(@PathVariable String name, Pageable pageable){

        Page<Prato> result = pratoService.findByName(name, pageable);

        return new ResponseEntity<>(result,HttpStatus.FOUND);
    }

    @GetMapping(value = "/tipoPrato")
    public ResponseEntity<Page<Prato>> findByTypePrato(@RequestParam (value = "tipoPrato") EnumPrato enumPrato, Pageable pageable){

        Page<Prato> result = pratoService.findByTypePrato(enumPrato, pageable);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Prato> createPrato(@RequestBody Prato prato){

        Prato result = pratoService.createPrato(prato);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void deletePrato(@PathVariable UUID id){

        pratoService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable UUID id, @RequestBody Prato prato){

        Prato result = pratoService.updatePrato(id, prato);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
