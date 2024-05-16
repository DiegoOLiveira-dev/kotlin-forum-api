package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(var autores: List<Usuario>) {

    init {
        val autor = Usuario(
                id = 1,
                nome = "Paulo Jean",
                email = "paulo@email.com"
        )
        autores = listOf(autor)
    }

    fun buscarPorId(id: Long): Usuario {
        return autores.stream().filter { a ->
            a.id == id
        }.findFirst().get()
    }

}
