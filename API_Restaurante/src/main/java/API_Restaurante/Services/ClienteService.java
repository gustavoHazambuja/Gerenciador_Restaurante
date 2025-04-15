package API_Restaurante.Services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import API_Restaurante.Exceptions.ClienteException;
import API_Restaurante.Models.Cliente;
import API_Restaurante.Models.Reserva;
import API_Restaurante.Repositories.ClienteRepository;
import API_Restaurante.Repositories.ReservaRepository;
import API_Restaurante.Enums.EnumReserva;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Transactional(readOnly = true)
    public Page<Cliente> getAllClientes(Pageable pageable){

        Page<Cliente> result = clienteRepository.findAll(pageable);

        return result;
    }

    @Transactional(readOnly = true)
    public Page<Cliente> findByName(String name, Pageable pageable){

        Page<Cliente> result = clienteRepository.findByNameContainingIgnoreCase(name, pageable);

        return result;
    }

    @Transactional
    public Cliente addCliente(Cliente cliente){

        return clienteRepository.save(cliente);
    }

    @Transactional
    public boolean deleteCliente(UUID id){

        if(!clienteRepository.existsById(id)){
            throw new ClienteException("Cliente com o id " + id + " não encontrado.");
        }

        clienteRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean fazerReserva(UUID id){

        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() ->  new ClienteException("Reserva com o id " + id + " não encontrado."));

        if(reserva.getStatusReserva() == EnumReserva.OCUPADA){
            throw new ClienteException("Reserva já ocupada.");
        }

        reserva.setStatusReserva(EnumReserva.OCUPADA);
        return true;
    }
}
