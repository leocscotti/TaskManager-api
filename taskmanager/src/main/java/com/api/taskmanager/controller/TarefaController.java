@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService service;

    @GetMapping
    public Page<TarefaResponse> listar(Pageable pageable) {
        return service.listar(pageable);
    }

    @GetMapping("/{id}")
    public TarefaResponse buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public TarefaResponse criar(@Valid @RequestBody TarefaRequest dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public TarefaResponse atualizar(@PathVariable Long id,
                                       @Valid @RequestBody TarefaRequest dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}