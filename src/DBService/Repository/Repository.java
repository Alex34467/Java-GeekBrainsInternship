package DBService.Repository;

import java.util.Collection;

// Интерфейс репозитория.
public interface Repository<T>
{
    // Добавление элемента.
    void add(T element);

    // Получение элемента по id.
    T getById(int id);

    // Получение всех элементов.
    Collection<T> getAll();

    // Обновление элемента.
    void update(T item);

    // Удаление элемента.
    void delete(T item);
}
