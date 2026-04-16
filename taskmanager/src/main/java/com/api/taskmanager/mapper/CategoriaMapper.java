@Component
public class CategoriaMapper {

    public Categoria toEntity(CategoriaRequest dto) {
        Categoria c = new Categoria();
        c.setNome(dto.getNome());
        return c;
    }

    public CategoriaResponse toDTO(Categoria c) {
        return new CategoriaResponse(c.getId(), c.getNome());
    }
}