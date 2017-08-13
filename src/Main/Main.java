package Main;

import DBService.DBService;
import Entities.*;
import PageProcessing.PageProcessor;
import java.util.Collection;


// Основной класс.
public class Main
{
    // Точка входа.
    public static void main(String[] args)
    {
        // Данные.
        PageProcessor pageProcessor = new PageProcessor();

        // Получение списка личностей.
        Collection<Person> persons = DBService.getInstance().getPersons();
        System.out.println(persons);

        // Получение списка сайтов.
        Collection<Site> sites = DBService.getInstance().getSites();
        System.out.println(sites);

        // Обход сайтов.
        //pageProcessor.processSites(sites);
    }
}
