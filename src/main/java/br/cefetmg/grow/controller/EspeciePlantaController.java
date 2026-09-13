package br.cefetmg.grow.controller;

import br.cefetmg.grow.dto.EspeciePlantaRequestDTO;
import br.cefetmg.grow.dto.EspeciePlantaResponseDTO;
import br.cefetmg.grow.service.CloudinaryService;
import br.cefetmg.grow.service.EspeciePlantaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/especies")
@RequiredArgsConstructor
@Tag(name = "Espécies de Plantas", description = "Endpoints para gerenciamento de espécies de plantas")
public class EspeciePlantaController {

    private final EspeciePlantaService especiePlantaService;
    private final CloudinaryService cloudinaryService;

    @GetMapping
    @Operation(summary = "Listar todas as espécies")
    public ResponseEntity<List<EspeciePlantaResponseDTO>> listar() {
        return ResponseEntity.ok(especiePlantaService.listar());
    }

    @GetMapping("/publicas")
    @Operation(summary = "Listar apenas espécies públicas")
    public ResponseEntity<List<EspeciePlantaResponseDTO>> listarPublicas() {
        return ResponseEntity.ok(especiePlantaService.listarPublicas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar espécie por ID")
    public ResponseEntity<EspeciePlantaResponseDTO> buscarPorId(
            @Parameter(description = "ID da espécie", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(especiePlantaService.buscarPorId(id));
    }

    @GetMapping("/buscar/nome-popular")
    @Operation(summary = "Buscar espécies por nome popular (contém)")
    public ResponseEntity<List<EspeciePlantaResponseDTO>> buscarPorNomePopular(
            @RequestParam String nome) {
        return ResponseEntity.ok(especiePlantaService.buscarPorNomePopular(nome));
    }

    @GetMapping("/buscar/nome-cientifico")
    @Operation(summary = "Buscar espécies por nome científico (contém)")
    public ResponseEntity<List<EspeciePlantaResponseDTO>> buscarPorNomeCientifico(
            @RequestParam String nome) {
        return ResponseEntity.ok(especiePlantaService.buscarPorNomeCientifico(nome));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Cadastrar nova espécie com upload de imagem")
    public ResponseEntity<EspeciePlantaResponseDTO> inserir(
            @Valid @RequestPart("dto") EspeciePlantaRequestDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(file);
            dto.setImagem(imageUrl);
        }

        EspeciePlantaResponseDTO criada = especiePlantaService.inserir(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Atualizar espécie existente com upload de imagem opcional")
    public ResponseEntity<EspeciePlantaResponseDTO> atualizar(
            @Parameter(description = "ID da espécie", required = true)
            @PathVariable Long id,
            @Valid @RequestPart("dto") EspeciePlantaRequestDTO dto,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        if (file != null && !file.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(file);
            dto.setImagem(imageUrl);
        }

        return ResponseEntity.ok(especiePlantaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir espécie")
    public ResponseEntity<Void> excluir(
            @Parameter(description = "ID da espécie", required = true)
            @PathVariable Long id) {
        especiePlantaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}