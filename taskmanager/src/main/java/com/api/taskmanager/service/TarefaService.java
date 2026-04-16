@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final TarefaMapper mapper;

    public Page<TarefaResponse> listar(Pageable pageable) {
        return tarefaRepository.findAll(pageable)
                .map(mapper::toDTO);
    }

    public TarefaResponse buscarPorId(Long id) {
        Tarefa t = tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada"));

        return mapper.toDTO(t);
    }

    public TarefaResponse criar(TarefaRequest dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

        Tarefa tarefa = mapper.toEntity(dto);
        tarefa.setUsuario(usuario);
        tarefa.setCategoria(categoria);

        return mapper.toDTO(tarefaRepository.save(tarefa));
    }

    public TarefaResponse atualizar(Long id, TarefaRequest dto) {

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Tarefa não encontrada"));

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setUsuario(usuario);
        tarefa.setCategoria(categoria);

        return mapper.toDTO(tarefaRepository.save(tarefa));
    }

    public void deletar(Long id) {
        if (!tarefaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Tarefa não encontrada");
        }
        tarefaRepository.deleteById(id);
    }
}