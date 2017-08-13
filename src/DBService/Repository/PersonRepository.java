package DBService.Repository;

import Entities.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

// Репизиторий личности.
public class PersonRepository implements Repository<Person>
{
    // Добавление.
    @Override
    public void add(Person person)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Person getById(int id)
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Person person)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Person person)
    {
        throw new NotImplementedException();
    }
}
