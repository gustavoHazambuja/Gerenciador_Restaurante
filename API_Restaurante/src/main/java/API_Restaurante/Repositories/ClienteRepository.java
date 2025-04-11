package API_Restaurante.Repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import API_Restaurante.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    
    Page<Cliente> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
