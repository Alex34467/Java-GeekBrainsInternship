package PageProcessing;

import DBService.DBService;
import Entities.*;
import Util.Util;
import java.util.*;

// Класс, управляющий краулерами.
public class CrawlerManager
{
    // Данные.
    private Stack<Page> pages;
    private Set<Thread> threads;
    private static Collection<Person> persons;


    // Конструктор.
    public CrawlerManager()
    {
        System.out.println("Crawler manager started.");
        threads = new HashSet<>();
        pages = new Stack<>();
    }

    // Работа.
    public void start(int processorsCount)
    {
        // Получение списка личностей.
        addPersons();

        // Поиск новых страниц.
        findNewSites();

        // Данные.
        startProcessors(processorsCount);
    }

    // Возврат страницы.
    public synchronized Page getPage()
    {
        // Добавление страниц.
        if (pages.empty())
        {
            addPages(100);
        }

        // Возврат страницы.
        return pages.pop();
    }

    // Возврат личностей.
    public Collection<Person> getPersons()
    {
        return persons;
    }

    // Получение личностей и ключевых слов.
    private void addPersons()
    {
        // Получение личностей.
        persons = DBService.getInstance().getPersons();

        // Получение ключевых слов.
        for (Person person : persons)
        {
            Collection<Keyword> keywords = DBService.getInstance().getKeywordsByPersonId(person.getId());
            person.addKeywords(keywords);
        }
    }

    // Запуск процессоров.
    private void startProcessors(int count)
    {
        // Создание процессоров.
        for (int i = 0; i < count; i++)
        {
            PageProcessor processor = new PageProcessor(this, "Proc " + i);
            threads.add(new Thread(processor));
        }

        // Запуск.
        for (Thread thread : threads)
        {
            thread.start();
        }
    }

    // Поиск новых сайтов.
    private void findNewSites()
    {
        // Получение списка сайтов.
        Collection<Site> sites = DBService.getInstance().getSitesWthoutPages();

        // Добавление robots.txt.
        for (Site site : sites)
        {
            // Подготовка страницы.
            String url = "http://" + site.getName() + "/robots.txt";
            Page page = new Page(url, site.getId(), Util.getCuttentDateTime(), null);

            // Добавление страницы.
            DBService.getInstance().addPage(page);
        }
    }

    // Добавление страниц.
    private void addPages(int count)
    {
        // Получение страниц.
        System.out.println("Adding " + count + " pages.");
        Collection<Page> pages2 = DBService.getInstance().getUnscannedPages(count);

        // Добавление страниц.
        for (Page page : pages2)
        {
            this.pages.push(page);
        }
    }
}
