package br.com.alura.forum.controllers

import br.com.alura.forum.dto.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.dto.presenters.TopicoPresenter
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.TopicoService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {

    @GetMapping()
    fun listar(@RequestParam(required = false) nomeCurso: String?): List<TopicoPresenter>{
        return service.listar(nomeCurso)
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoPresenter{
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
            @RequestBody @Valid topico: NovoTopicoDTO,
            uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoPresenter> {
        val topicoPresenter = service.cadastrar(topico)
        val uri = uriBuilder.path("/topicos/${topicoPresenter.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoPresenter)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid topico: AtualizacaoTopicoDTO): ResponseEntity<TopicoPresenter>{
        val topicoPresenter = service.atualizar(topico)
        return ResponseEntity.ok(topicoPresenter)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long){
        return service.deletar(id)
    }

}