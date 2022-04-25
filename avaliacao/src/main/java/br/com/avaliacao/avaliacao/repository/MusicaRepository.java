package br.com.avaliacao.avaliacao.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.avaliacao.avaliacao.model.Musica;

public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
