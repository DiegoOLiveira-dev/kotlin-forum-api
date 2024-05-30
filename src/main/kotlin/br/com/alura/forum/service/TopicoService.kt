package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.dto.presenters.TopicoPresenter
import br.com.alura.forum.exceptions.NotFoundException
import br.com.alura.forum.mappers.NovoTopicoMapper
import br.com.alura.forum.mappers.TopicoPresenterMapper
import br.com.alura.forum.model.Topico
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private val repository: TopicoRepository,
        private var topicoPresenterMapper: TopicoPresenterMapper,
        private var topicoNovoTopicoMapper: NovoTopicoMapper
        ) {


    fun listar(): List<TopicoPresenter> {
        return repository.findAll().stream().map { t -> topicoPresenterMapper.map(t)}.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoPresenter {
        val topico = repository.findById(id)
                .orElseThrow{NotFoundException("Topico inexistente")}
        return topicoPresenterMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDTO): TopicoPresenter {
        val topico = topicoNovoTopicoMapper.map(dto)
        repository.save(topico)
        return topicoPresenterMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoDTO): TopicoPresenter {
        val topico = repository.findById(form.id).orElseThrow{NotFoundException("Topico inexistente")}

        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        return topicoPresenterMapper.map(topico)
    }

    fun deletar(id: Long) {
       repository.deleteById(id)
    }

}