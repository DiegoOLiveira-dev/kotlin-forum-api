package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import kotlin.math.min

data class AtualizacaoTopicoDTO (
        @field:NotNull
        val id: Long,
        @field:NotEmpty
        @field:Size(min = 5, max = 100)
        val titulo: String,
        @field:NotEmpty
        val mensagem: String
)
