package com.webservice.eventfye.Service;

import com.webservice.eventfye.Model.Palestra;
import com.webservice.eventfye.Repository.PalestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PalestraService {

    @Autowired
    private PalestraRepository repository;

    public Palestra insert(Palestra palestra){
        Optional<Palestra> optionalPalestra = repository.findByValues(palestra.getNomePalestra(),palestra.getNomePalestrante(),palestra.getHorarioPalestra());
        if (optionalPalestra.isPresent()){
            throw new IllegalArgumentException("Esse evento já existe!");
        }
        return repository.save(palestra);
    }
}
