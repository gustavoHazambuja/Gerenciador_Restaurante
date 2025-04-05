package API_Restaurante.Services;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import API_Restaurante.Exceptions.PratoException;
import API_Restaurante.Models.Prato;
import API_Restaurante.Repositories.PratoRepository;

@Service
public class PratoService {
    
    @Autowired
    private PratoRepository pratoRepository;


    @Transactional(readOnly = true)
    public Page<Prato> getPratos(Pageable pageable){
        
        Page<Prato> result = pratoRepository.findAll(pageable);

        return result;
    }

    @Transactional(readOnly =  true)
    public Page<Prato> findByName(String name, Pageable pageable){

        Page<Prato> result = pratoRepository.findByNameContainingIgnoreCase(name, pageable);

        return result;
    }

    @Transactional
    public Prato createPrato(Prato p){

        Prato result = pratoRepository.save(p);

        return result;
    }

    @Transactional
    public void deleteById(UUID id){

        if(!pratoRepository.existsById(id)){
            throw new PratoException("Prato com id " + id + " não encontrado.");
        }
        pratoRepository.deleteById(id);
    }

    @Transactional
    public Prato updatePrato(UUID id, Prato prato){

        if(!pratoRepository.existsById(id)){
            throw new PratoException("Prato com id " + id + " não encontrado.");
        }

        Prato result = pratoRepository.findById(id).get();

        if(prato.getName() != null){
            result.setName(prato.getName());
        }

        if(prato.getPrice() != null){
            result.setPrice(prato.getPrice());
        }

        if(prato.getTypePrato() != null){
            result.setTypePrato(prato.getTypePrato());
        }

        return pratoRepository.save(result);

    }
}
