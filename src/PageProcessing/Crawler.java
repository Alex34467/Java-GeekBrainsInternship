package PageProcessing;

import DBService.DBService;
import Entities.Page;
import Entities.Site;
import Util.Util;
import java.util.Collection;

// Обработчик страниц.
public class Crawler
{
    // Данные.
    PageProcessor pageProcessor;

    // Конструктор.
    public Crawler()
    {
        pageProcessor = new PageProcessor();
    }

    // Запуск.
    public void start()
    {
        // Поиск новых сайтов.
        findNewSites();

        // Обход страниц.
        processPages();
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

    // Обход страниц.
    private void processPages()
    {
        // Получение непросканированной страницы.
        Page page = DBService.getInstance().getUnscannedPage();

        // Анализ страницы.
        String url = page.getUrl().toLowerCase();
        if (url.endsWith("robots.txt"))
        {
            pageProcessor.processRobots(page);
        }
        else if (url.endsWith("sitemap.xml"))
        {
            pageProcessor.processSitemap(page);
        }
        else
        {
            pageProcessor.processPage(page);
        }
    }
}