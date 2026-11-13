package biblioteca.repositorio;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T,ID> {
    T salvar(T item);
    Optional<T> buscarPorId(ID id);
    List<T> listarTodos();
    Optional<T> remover(ID id);
}
