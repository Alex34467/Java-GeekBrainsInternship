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
    private boolean isRunning = true;

    // Конструктор.
    public Crawler()
    {
        pageProcessor = new PageProcessor();
    }

    // Запуск.
    public void start()
    {
        while (isRunning)
        {
            // Поиск новых сайтов.
            findNewSites();

            // Обход страниц.
            processPages();
        }
    }

    // Поиск новых сайтов.
    private void findNewSites()
    {
        // Получение списка сайтов.
        System.out.println("   Looking for sites without pages...");
        Collection<Site> sites = DBService.getInstance().getSitesWthoutPages();

        // Добавление robots.txt.
        System.out.println("   Found " + sites.size() + " sites.");
        for (Site site : sites)
        {
            // Подготовка страницы.
            String url = "http://" + site.getName() + "/robots.txt";
            Page page = new Page(url, site.getId(), Util.getCuttentDateTime(), null);

            // Добавление страницы.
            DBService.getInstance().addPage(page);
        }
        System.out.println("   Site searching completed.");
    }

    // Обход страниц.
    private void processPages()
    {
        // Получение непросканированной страницы.
        Page page = DBService.getInstance().getUnscannedPage();

        if (page != null)
        {
            // Анализ страницы.
            System.out.println("Process page: " + page.getUrl());
            String url = page.getUrl().toLowerCase();
            if (url.endsWith("robots.txt"))
            {
                System.out.println("   Its robots.txt page.");
                pageProcessor.processRobots(page);
            }
            else if (url.endsWith("sitemap.xml"))
            {
                System.out.println("   Its Sitemap.xml page.");
                pageProcessor.processSitemap(page);
            }
            else
            {
                System.out.println("   Its usual page.");
                pageProcessor.processPage(page);
            }

            // Обновление информации о странице.
            DBService.getInstance().updatePageScanDate(page, Util.getCuttentDateTime());
            System.out.println("Page processed.");
        }
        else
        {
            System.out.println("All pages proceeded.");
            isRunning = false;
        }
    }
}