package DBService.Repository;

// Интерфейс репозитория.
public interface Repository<T>
{
    // Добавление элемента.
    void add(T element);

    // Получение элемента по id.
    T getById(int id);

    // Обновление элемента.
    void update(T item);

    // Удаление элемента.
    void delete(T item);
}
