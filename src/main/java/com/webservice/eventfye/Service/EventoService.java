package com.webservice.eventfye.Service;

import com.webservice.eventfye.Controller.Request.EventoRequest;
import com.webservice.eventfye.Model.Evento;
import com.webservice.eventfye.Repository.EventoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository repository;

    public Evento insert(Evento evento, String idUsuario){
        Optional<Evento> optionalEvento = repository.findByValues(idUsuario, evento.getNomeEvento(),evento.getDataInicioEvento(),evento.getDataFimEvento(),evento.getLocalEvento(),evento.getLinkEvento());
        if(optionalEvento.isPresent()){
            throw new IllegalArgumentException("Esse evento já existe!");
        }
        evento.setIdUsuario(idUsuario);
        return repository.save(evento);
    }

    public void delete(Long id){
        try{
            repository.deleteById(id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Não foi possível deletar o evento com id: " + id + ", evento não encontrado.");
        }

    }

    public List<Evento> findAll(){
        return repository.findAll();
    }

    public Evento findById(Long id){
        Optional<Evento> optionalEvento = Optional.ofNullable(repository.findByIdEvento(id));
        if(optionalEvento.isEmpty()){
            throw new EntityExistsException("Evento não encontrado");
        }
        return optionalEvento.get();
    }

    public void update(Long id, EventoRequest evento){
        try{
        Evento entidade = repository.getReferenceById(id);

        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Evento com id" + id + " não encontrado.");
        }
    }

}
