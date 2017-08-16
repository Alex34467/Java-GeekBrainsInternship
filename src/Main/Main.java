package Main;

import PageProcessing.CrawlerManager;

// Основной класс.
public class Main
{
    // Точка входа.
    public static void main(String[] args)
    {
        // Запуск краулера.
        new CrawlerManager().start(60);
    }
}
