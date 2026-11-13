package biblioteca.repositorio;

import java.util.*;
import java.util.function.Function;

public class RepositorioEmMemoria<T, ID> implements Repositorio<T, ID> {

    private final Map<ID, T> armazenamento = new HashMap<>();
    private final Function<T, ID> extratorId;

    public RepositorioEmMemoria(Function<T, ID> extratorId) {
        this.extratorId = extratorId;
    }


    @Override
    public T salvar(T item) {
        armazenamento.put(extratorId.apply(item), item);
        return item;
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(armazenamento.get(id));
    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(armazenamento.values());
    }

    @Override
    public Optional<T> remover(ID id) {
        return Optional.ofNullable(armazenamento.remove(id));
    }
}