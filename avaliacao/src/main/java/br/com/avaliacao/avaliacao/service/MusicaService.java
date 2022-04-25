package br.com.avaliacao.avaliacao.service;

import java.util.List;
import java.util.Optional;

import br.com.avaliacao.avaliacao.model.Musica;
import br.com.avaliacao.avaliacao.shared.MusicaDto;

public interface MusicaService {
    MusicaDto criarMusica(MusicaDto musica);
    List<MusicaDto> obterMusicas();
    Optional<MusicaDto> obterPorId(String id);
    MusicaDto atualizarMusica(String id, MusicaDto musica);
    void removerMusica(String id);
}
