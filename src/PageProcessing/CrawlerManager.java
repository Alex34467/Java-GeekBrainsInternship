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
    private Set<String> processingLinks;
    private final int addPagesCount = 100;
    private boolean unscannedPages = true;


    // Конструктор.
    public CrawlerManager()
    {
        System.out.println("Crawler manager started.");
        threads = new HashSet<>();
        pages = new Stack<>();
        processingLinks = new HashSet<>();
    }

    // Работа.
    public void start(int processorsCount)
    {
        // Получение списка личностей.
        addPersons();

        // Поиск новых сайтов.
        findNewSites();

        // Запуск обработчиков.
        startProcessors(processorsCount);
    }

    // Возврат страницы.
    public synchronized Page getPage()
    {
        // Добавление страниц.
        if (pages.empty())
        {
            addPages(addPagesCount);
        }

        // Возврат страницы.
        Page page = pages.pop();
        processingLinks.add(page.getUrl());
        return page;
    }

    // Завершение обхода страницы.
    public synchronized void completePageProcessing(Page page)
    {
        processingLinks.remove(page.getUrl());
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
        Collection<Page> pages;
        if (unscannedPages)
        {
            // Получение непросканированных страниц.
            System.out.println("Adding " + count + " unscanned pages.");
            pages = DBService.getInstance().getUnscannedPages(count);

            // Если непросканированные страницы закончились.
            if (pages.isEmpty())
            {
                // Вывод информации.
                System.out.println("Unscanned pages not found.");
                System.out.println("Changing state to search sitemap pages.");

                // Переключение состояния.
                unscannedPages = false;

                // Повторный вызов.
                addPages(count);
            }
        }
        else
        {
            // Sitemap страницы.
            System.out.println("Adding " + count + " sitemap pages.");
            pages = DBService.getInstance().getSitemapPages(count);

            // Если нет Sitemap страниц.
            if (pages.isEmpty())
            {
                // Остановка.
                System.out.println("Sitemap pages not found. Stopping.");
                stopProcessors();
                return;
            }
        }

        // Добавление страниц.
        for (Page page : pages)
        {
            if (!processingLinks.contains(page.getUrl()))
            {
                this.pages.push(page);
            }
        }

        // Проверка.
        if (this.pages.isEmpty())
        {
            addPages(addPagesCount);
        }
    }

    // Остановка обработчиков.
    private void stopProcessors()
    {
        for (Thread thread : threads)
        {
            thread.interrupt();
        }
    }
}
