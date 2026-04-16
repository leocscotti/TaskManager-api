@Component
public class TarefaMapper {

    public Tarefa toEntity(TarefaRequest dto) {
        Tarefa t = new Tarefa();
        t.setTitulo(dto.getTitulo());
        t.setDescricao(dto.getDescricao());
        t.setPrioridade(dto.getPrioridade());
        return t;
    }

    public TarefaResponse toDTO(Tarefa t) {
        return new TarefaResponse(
                t.getId(),
                t.getTitulo(),
                t.getDescricao(),
                t.getPrioridade(),
                t.getConcluida(),
                t.getUsuario().getNome(),
                t.getCategoria().getNome()
        );
    }
}