package DBService.Repository;

import DBService.DBExecutor;
import Entities.PersonsPageRank;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Collection;

// Репозиторий количества упоминаний личности на странице.
public class PersonsPageRankRepository implements Repository<PersonsPageRank>
{
    // Данные.
    private DBExecutor executor;

    // Конструктор.
    public PersonsPageRankRepository(DBExecutor executor)
    {
        this.executor = executor;
    }

    // Добавление.
    @Override
    public void add(PersonsPageRank element)
    {
        // Подготовка запроса.
        String query = "INSERT INTO PersonsPageRank (PersonId, PageId, Rank) VALUES (" + element.getPersonId() + ", " + element.getPageId() + ", " + element.getRank() + ")";
        executor.executeUpdate(query);
    }

    // Получение по Id.
    @Override
    public PersonsPageRank getById(int id)
    {
        throw new NotImplementedException();
    }

    // Получение всех записей.
    @Override
    public Collection<PersonsPageRank> getAll()
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(PersonsPageRank item)
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(PersonsPageRank item)
    {
        throw new NotImplementedException();
    }
}
