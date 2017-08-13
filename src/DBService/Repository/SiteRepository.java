package DBService.Repository;

import Entities.Site;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Collection;


// Репозиторий сайта.
public class SiteRepository implements Repository<Site>
{
    // Добавление.
    @Override
    public void add(Site site)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Site getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор всех сайтов.
    @Override
    public Collection<Site> getAll()
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Site site)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Site site)
    {
        throw new NotImplementedException();
    }
}
