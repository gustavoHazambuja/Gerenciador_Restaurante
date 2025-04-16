package API_Restaurante.Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import API_Restaurante.Exceptions.ReservaException;
import API_Restaurante.Models.Reserva;
import API_Restaurante.Repositories.ReservaRepository;

@Service
public class ReservaService {

    
    @Autowired
    private ReservaRepository reservaRepository;

   @Transactional(readOnly = true) 
   public Page<Reserva> getAll(Pageable pageable){

        Page<Reserva> result = reservaRepository.findAll(pageable);

        return result;
   }

   @Transactional(readOnly = true)
   public Page<Reserva> findByCapacidade(Integer capacidade, Pageable pageable){

        Page<Reserva> result = reservaRepository.findByCapacidade(capacidade, pageable);

        return result;
   }

   @Transactional
   public boolean deleteById(UUID id){

       if(!reservaRepository.existsById(id)){
            throw new ReservaException("Reservacom o id " + id + " não encontrado.");
       }

       reservaRepository.deleteById(id);
       return true;
   }

   @Transactional
   public Reserva createReserva(Reserva reserva){

        Reserva result = reservaRepository.save(reserva);

        return result;
   }

   @Transactional
   public Reserva updateReserva(UUID id, Reserva reserva){

    if(!reservaRepository.existsById(id)){
        throw new ReservaException("Reservacom o id " + id + " não encontrado.");
     }

     Reserva result = reservaRepository.findById(id).get();

        if(reserva.getCapacidade() != null){
            result.setCapacidade(reserva.getCapacidade());
        }

        if(reserva.getStatusReserva() != null){
            result.setStatusReserva(reserva.getStatusReserva());
        }

        return reservaRepository.save(result);
   }
}
