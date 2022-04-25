package br.com.avaliacao.avaliacao.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.avaliacao.avaliacao.model.Musica;
import br.com.avaliacao.avaliacao.repository.MusicaRepository;
import br.com.avaliacao.avaliacao.service.MusicaService;
import br.com.avaliacao.avaliacao.shared.MusicaDto;
import br.com.avaliacao.avaliacao.view.model.MusicaRequest;
import br.com.avaliacao.avaliacao.view.model.MusicaResponse;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    @Autowired
    private MusicaService servico;
    ModelMapper mapperMusic = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<MusicaResponse>> obterMusicas() {
        List<MusicaDto> MuDto = servico.obterMusicas();
        List<MusicaResponse> MusicResponse = MuDto.stream()
        .map(d -> mapperMusic.map(d, MusicaResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(MusicResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MusicaResponse> criarMusica(@RequestBody MusicaRequest musicaRequest) {
        MusicaDto Mdto = mapperMusic.map(musicaRequest, MusicaDto.class);
        Mdto = servico.criarMusica(Mdto);
        MusicaResponse responseMusic = mapperMusic.map(Mdto, MusicaResponse.class);
        return new ResponseEntity<>(responseMusic, HttpStatus.CREATED);
    }
    
    @GetMapping(value="/{id2}")
    public ResponseEntity<MusicaResponse> obterPorId(@PathVariable String id) {
        Optional<MusicaDto> pessoaDto = servico.obterPorId(id);

        if(pessoaDto.isPresent()) {
            MusicaResponse MusiResponse = mapperMusic.map(pessoaDto.get(), MusicaResponse.class);
            return new ResponseEntity<>(MusiResponse, HttpStatus.FOUND);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<MusicaResponse> atualizarMusica(@PathVariable String id, @RequestBody MusicaRequest request) {
        MusicaDto musicaDto = mapperMusic.map(request, MusicaDto.class);
        musicaDto = servico.atualizarMusica(id, musicaDto);
        MusicaResponse response = mapperMusic.map(musicaDto, MusicaResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> removerMusica(@PathVariable String id) {
        servico.removerMusica(id);
        return new ResponseEntity<String>("Removido com sucesso!", HttpStatus.OK);
    }
}