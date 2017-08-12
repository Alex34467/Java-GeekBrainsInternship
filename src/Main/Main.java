package Main;

import DBService.DBService;
import Entities.Person;
import Entities.Site;
import PageProcessing.PageProcessor;
import java.util.Set;

// Основной класс.
public class Main
{
    // Точка входа.
    public static void main(String[] args)
    {
        // Данные.
        PageProcessor pageProcessor = new PageProcessor();

        // Получение списка личностей.
        Set<Person> persons = DBService.getInstance().getPersons();
        System.out.println();

        // Получение списка сайтов.
        Set<Site> sites = DBService.getInstance().getSites();
        System.out.println();

        // Обход сайтов.
        pageProcessor.processSites(sites);
    }
}
