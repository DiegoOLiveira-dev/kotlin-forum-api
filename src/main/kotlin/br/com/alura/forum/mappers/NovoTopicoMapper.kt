package br.com.alura.forum.mappers

import br.com.alura.forum.dto.NovoTopicoDTO
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoMapper(
        private var cursoService: CursoService,
        private var autorService: UsuarioService
    ): Mapper<NovoTopicoDTO, Topico> {
    override fun map(t: NovoTopicoDTO): Topico {
        return Topico(
                titulo = t.titulo,
                mensagem = t.mensagem,
                curso = cursoService.buscarPorId(t.idCurso),
                autor = autorService.buscarPorId(t.idCurso),

                )
    }

}
