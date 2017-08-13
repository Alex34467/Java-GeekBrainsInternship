package DBService.Repository;

import Entities.Keyword;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Set;

// Репозиторий ключевых слов.
public class KeywordRepository implements Repository<Keyword>
{
    // Добавление.
    @Override
    public void add(Keyword keyword)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Keyword getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор ключевых слов по Id личности.
    public Set<Keyword> getKeywordsByPersonId(int id)
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Keyword keyword)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Keyword keyword)
    {
        throw new NotImplementedException();
    }
}
