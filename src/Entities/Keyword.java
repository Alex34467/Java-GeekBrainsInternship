package Entities;

// Ключевок слово.
public class Keyword
{
    // Данные.
    private int id;
    private String name;
    private int personId;


    // Конструктор.
    public Keyword(final int id, final String name, final int personId)
    {
        this.id = id;
        this.name = name;
        this.personId = personId;
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

    public int getPersonId()
    {
        return personId;
    }

    // Строка.
    @Override
    public String toString()
    {
        return "Id: " + id + " Name: " + name + " PersonId: " + personId;
    }
}
