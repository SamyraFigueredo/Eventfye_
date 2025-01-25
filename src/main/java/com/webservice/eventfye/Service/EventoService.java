package com.webservice.eventfye.Service;

import com.webservice.eventfye.Controller.Request.EventoRequest;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Repository.EventoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento insert(Evento evento){
        return repository.save(evento);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não foi possível deletar o evento com id: " + id + ", evento não encontrado.");
        }

    }

    public void update(Long id, EventoRequest evento){
        try{
        Evento entidade = repository.getReferenceById(id);

        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Evento com id" + id + " não encontrado.");
        }
    }
}
