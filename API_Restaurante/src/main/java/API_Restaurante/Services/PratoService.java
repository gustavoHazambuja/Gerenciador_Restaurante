package API_Restaurante.Services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<Prato> findByName(String name, Pageable pageable){

        Page<Prato> result = pratoRepository.findByNameContainingIgnoreCase(name, pageable);

        return result;
    }
}
