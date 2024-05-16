package br.com.alura.forum.mappers

import br.com.alura.forum.dto.presenters.TopicoPresenter
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoPresenterMapper: Mapper<Topico, TopicoPresenter> {
    override fun map(t: Topico): TopicoPresenter {
        return TopicoPresenter(
                id = t.id,
                titulo = t.titulo,
                mensagem = t.mensagem,
                dataCriacao = t.dataCriacao,
                status = t.status
        )
    }


}