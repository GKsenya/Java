package Lesson_6;

public class BookBuilderImpl implements BookBuilder{
    private Book book;

    BookBuilderImpl(){
        book = new Book();
    }

    @Override
    public Book build() {
        return book;
    }

    @Override
    public BookBuilder title(String title) {
        book.setTitle(title);
        return this;
    }

    @Override
    public BookBuilder bookCover(String bookCover) {
        book.setBookCover(bookCover);
        return this;
    }

    @Override
    public BookBuilder pages(int pages) {
        book.setPages(pages);
        return this;
    }

    @Override
    public BookBuilder year(int year) {
        book.setYear(year);
        return this;
    }

    @Override
    public BookBuilder bookCoverColour(String colour) {
        book.setBookCoverColor(colour);
        return this;
    }
}
