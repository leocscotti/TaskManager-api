@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Page<UsuarioResponse> listar(Pageable pageable) {
        return repository.findAll(pageable)
                .map(u -> new UsuarioResponse(u.getId(), u.getNome(), u.getEmail()));
    }

    public UsuarioResponse buscarPorId(Long id) {
        Usuario u = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail());
    }

    public UsuarioResponse criar(UsuarioRequest dto) {
        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());

        u = repository.save(u);

        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail());
    }

    public UsuarioResponse atualizar(Long id, UsuarioRequest dto) {
        Usuario u = repositoy.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado"));

        u.setNome(dto.getNome());
        u.setEmail(dto.getEmail());

        return new UsuarioResponse(u.getId(), u.getNome(), u.getEmail());
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}