package DBService.Repository;

import Entities.Person;
import DBService.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;


// Репизиторий личности.
public class PersonRepository implements Repository<Person>
{
    // Данные.
    private DBExecutor executor;


    // Конструктор.
    public PersonRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

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
        // Подготовка запроса.
        String query = "SELECT * FROM Persons";
        ResultSet resultSet = executor.executeQuery(query);

        // Обработка результата.
        Collection<Person> persons = new HashSet<>();
        try
        {
            while (resultSet.next())
            {
                Person person = new Person(resultSet.getInt(1), resultSet.getString(2));
                persons.add(person);
            }
        }
        catch (SQLException e)
        {
            return persons;
        }

        // Возврат результата.
        return persons;
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
