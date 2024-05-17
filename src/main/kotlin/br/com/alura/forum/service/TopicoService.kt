package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizacaoTopicoDTO
import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.dto.presenters.TopicoPresenter
import br.com.alura.forum.mappers.NovoTopicoMapper
import br.com.alura.forum.mappers.TopicoPresenterMapper
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TopicoService(
        private var topicos: List<Topico>,
        private var topicoPresenterMapper: TopicoPresenterMapper,
        private var topicoNovoTopicoMapper: NovoTopicoMapper
        ) {


    fun listar(): List<TopicoPresenter> {
        return topicos.stream().map { t -> topicoPresenterMapper.map(t)}.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoPresenter {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        return topicoPresenterMapper.map(topico)
    }

    fun cadastrar(dto: NovoTopicoDTO) {
        val topico = topicoNovoTopicoMapper.map(dto)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }

    fun atualizar(topico: AtualizacaoTopicoDTO) {
        val topicoAnterior = topicos.stream().filter { t ->
            t.id == topico.id
        }.findFirst().get()

        topicos = topicos.minus(topicoAnterior).plus(Topico(
                id = topico.id,
                titulo = topico.titulo,
                mensagem = topico.mensagem,
                autor = topicoAnterior.autor,
                curso = topicoAnterior.curso,
                respostas = topicoAnterior.respostas
        ))
    }

    fun deletar(id: Long) {
        val topicoDelete = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()

        topicos = topicos.minus(topicoDelete)
    }

}