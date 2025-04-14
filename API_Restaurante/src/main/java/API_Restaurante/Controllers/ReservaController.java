package API_Restaurante.Controllers;

import java.util.UUID;

import org.hibernate.id.uuid.UuidGenerator;
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

import API_Restaurante.Models.Reserva;
import API_Restaurante.Services.ReservaService;

@RestController
@RequestMapping(value = "/reserva")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<Page<Reserva>> getAll(Pageable pageable){

        Page<Reserva> result = reservaService.getAll(pageable);
        
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping(value = "/filtrar")
    public ResponseEntity<Page<Reserva>> findByCapacidade(@RequestParam(value = "capacidade") Integer capacidade, Pageable pageable){

        Page<Reserva> result = reservaService.findByCapacidade(capacidade, pageable);

        return new ResponseEntity<>(result,HttpStatus.FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public boolean delete(@PathVariable UUID id){

        return reservaService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva){

        Reserva result = reservaService.createReserva(reserva);

        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Reserva> update(@PathVariable UUID id, @RequestBody Reserva reserva){

        Reserva result = reservaService.updateReserva(id, reserva);

        return new ResponseEntity<>(result,HttpStatus.OK);

    }
}
