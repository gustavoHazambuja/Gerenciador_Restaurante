package API_Restaurante.Repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import API_Restaurante.Enums.EnumPrato;
import API_Restaurante.Models.Prato;


public interface PratoRepository extends JpaRepository<Prato, Integer> {
    
    Page<Prato> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Prato> findByTypePrato(EnumPrato enumPrato, Pageable pageable);
}
