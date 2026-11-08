package biblioteca.repositorio;

import java.util.*;

public class RepositorioEmMemoria <T,ID> implements Repositorio<T,ID>{
    private final Map<ID, T> armazenamento = new HashMap<>();
    @Override
    public T salvar(ID id,T item) {
        armazenamento.put(id, item);
        return item;
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(armazenamento.get(id));    }

    @Override
    public List<T> listarTodos() {
        return new ArrayList<>(armazenamento.values());
    }

    @Override
    public void remover(ID id) {
        armazenamento.remove(id);
    }
}
