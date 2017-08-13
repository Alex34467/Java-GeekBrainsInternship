package DBService.Repository;

import Entities.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Collection;


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

    // Выбор всех личностей.
    @Override
    public Collection<Person> getAll()
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
