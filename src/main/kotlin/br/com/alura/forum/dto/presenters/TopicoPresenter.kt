package br.com.alura.forum.dto.presenters

import br.com.alura.forum.model.StatusTopico
import java.time.LocalDateTime

data class TopicoPresenter (
        val id: Long?,
        val titulo: String,
        val mensagem: String,
        val status: StatusTopico,
        val dataCriacao: LocalDateTime
)
