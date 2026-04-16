@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Page<CategoriaResponse> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(c -> new CategoriaResponse(c.getId(), c.getNome()));
    }

    public CategoriaResponse buscarPorId(Long id) {
        Categoria c = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

        return new CategoriaResponse(c.getId(), c.getNome());
    }

    public CategoriaResponse criar(CategoriaRequest dto) {
        Categoria c = new Categoria();
        c.setNome(dto.getNome());

        c = repository.save(c);

        return new CategoriaResponse(c.getId(), c.getNome());
    }

    public CategoriaResponse atualizar(Long id, CategoriaRequest dto) {
        Categoria c = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria não encontrada"));

        c.setNome(dto.getNome());

        return new CategoriaResponse(c.getId(), c.getNome());
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Categoria não encontrada");
        }
        repository.deleteById(id);
    }
}