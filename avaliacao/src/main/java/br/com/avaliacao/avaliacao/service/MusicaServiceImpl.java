package br.com.avaliacao.avaliacao.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.avaliacao.avaliacao.model.Musica;
import br.com.avaliacao.avaliacao.repository.MusicaRepository;
import br.com.avaliacao.avaliacao.shared.MusicaDto;

@Service
public class MusicaServiceImpl implements MusicaService {

    @Autowired
    private MusicaRepository repositorio;
    ModelMapper musicaMapper = new ModelMapper();

    @Override
    public MusicaDto criarMusica(MusicaDto musicaDto) {
        Musica musica = musicaMapper.map(musicaDto, Musica.class);
        musica = repositorio.save(musica);
        MusicaDto dtoMusic = musicaMapper.map(musica, MusicaDto.class);
        return dtoMusic;
    }

    @Override
    public List<MusicaDto> obterMusicas() {
        List<Musica> musicas = repositorio.findAll();
        List<MusicaDto> musicasDto = musicas.stream()
        .map(m -> musicaMapper.map(m, MusicaDto.class))
        .collect(Collectors.toList());
        return musicasDto;
    }

    @Override
    public Optional<MusicaDto> obterPorId(String id) {
        Optional<Musica> musica = repositorio.findById(id);

        if(musica.isPresent()) {
            MusicaDto musicaDto = musicaMapper.map(musica.get(), MusicaDto.class);
            return Optional.of(musicaDto);
        }
        return Optional.empty();
    }

    @Override
    public MusicaDto atualizarMusica(String id, MusicaDto musicaDto) {
        Musica MusiDto = musicaMapper.map(musicaDto, Musica.class);

        MusiDto.setId(id);
        MusiDto = repositorio.save(MusiDto);

        MusicaDto dtoMusi = musicaMapper.map(musicaDto, MusicaDto.class);
        return dtoMusi;
    }

    @Override
    public void removerMusica(String id) {
        repositorio.deleteById(id);
    }
    
}
