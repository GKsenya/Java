package Lesson_6;

public class Book {

    private String title;
    private String bookCover;
    private int pages;
    private int year;
    private String bookCoverColor;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setBookCoverColor(String bookCoverColor) {
        this.bookCoverColor = bookCoverColor;
    }

    @Override
    public String toString() {
        return "Название: '" + title + "'" +
                ", тип переплета: " + bookCover +
                ", количество страниц: " + pages +
                ", год издания: " + year +
                ", состояние: " + bookCoverColor + ";";
    }
}
