package API_Restaurante.Repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import API_Restaurante.Models.Reserva;


public interface ReservaRepository extends JpaRepository<Reserva,UUID> {
    
    Page<Reserva> findByCapacidade(Integer capacidade, Pageable pageable);
}
