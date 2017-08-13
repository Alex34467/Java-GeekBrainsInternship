package Entities;

import java.util.*;

// Класс сайта.
public class Site
{
    // Данные.
    private int id;
    private String name;
    private Collection<Page> pages;


    // Конструктор.
    public Site(final int id, final String name)
    {
        this.id = id;
        this.name = name;
        pages = new HashSet<>();
    }

    // Добавление страницы.
    public void addPage(Page page)
    {
        pages.add(page);
    }

    // Добавление страниц.
    public void addPages(Collection<Page> pages)
    {
        this.pages.addAll(pages);
    }

    // Геттеры.
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public Collection<Page> getPages()
    {
        return pages;
    }

    // Строка.
    @Override
    public String toString()
    {
        return "Id: " + id + " Name: " + name;
    }
}
