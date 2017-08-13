package DBService.Repository;

import Entities.Page;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.Collection;


// Репозиторий странцы сайта.
public class PageRepository implements Repository<Page>
{
    // Добавление.
    @Override
    public void add(Page page)
    {
        throw new NotImplementedException();
    }

    // Выбор.
    @Override
    public Page getById(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор всех страниц по Id сайта.
    public Collection<Page> getAllPagesBySiteId(int id)
    {
        throw new NotImplementedException();
    }

    // Выбор всех страниц.
    @Override
    public Collection<Page> getAll()
    {
        throw new NotImplementedException();
    }

    // Удаление.
    @Override
    public void delete(Page page)
    {
        throw new NotImplementedException();
    }

    // Обновление.
    @Override
    public void update(Page page)
    {
        throw new NotImplementedException();
    }
}
