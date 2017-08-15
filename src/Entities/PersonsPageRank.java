package Entities;

// Количество совпадений с ключевыми словами на странице.
public class PersonsPageRank
{
    // Данные.
    private int personId;
    private int pageId;
    private int rank;


    // Конструктор.
    public PersonsPageRank(final int personId, final int pageId, final int rank)
    {
        this.personId = personId;
        this.pageId = pageId;
        this.rank = rank;
    }

    // Геттеры.
    public int getPersonId()
    {
        return personId;
    }

    public int getPageId()
    {
        return pageId;
    }

    public int getRank()
    {
        return rank;
    }

    // Строка.
    @Override
    public String toString()
    {
        return "PersonId: " + personId + ", PageId: " + pageId + ", Rank: " + rank;
    }
}
