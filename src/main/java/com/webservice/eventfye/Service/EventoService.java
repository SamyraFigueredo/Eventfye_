package com.webservice.eventfye.Service;

import com.webservice.eventfye.Controller.Request.EventoRequest;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento insert(Evento evento){
        Optional<Evento> optionalEvento = repository.findByValues(evento.getNomeEvento(),evento.getDataInicioEvento(),evento.getDataFimEvento(),evento.getLocalEvento(),evento.getLinkEvento());
        if(optionalEvento.isEmpty()){
            throw new IllegalArgumentException("Esse evento já existe!");
        }
        return repository.save(evento);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não foi possível deletar o evento com id: " + id + ", evento não encontrado.");
        }

    }

    public Evento findById(Long id){
        return repository.findByIdEvento(id);
    }

    public void update(Long id, EventoRequest evento){
        try{
        Evento entidade = repository.getReferenceById(id);

        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Evento com id" + id + " não encontrado.");
        }
    }
}
