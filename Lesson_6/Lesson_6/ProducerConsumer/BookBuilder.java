package Lesson_6;

public interface BookBuilder {
    Book build();
    BookBuilder title(String title);
    BookBuilder bookCover(String bookCover);
    BookBuilder pages(int pages);
    BookBuilder year(int year);
    BookBuilder bookCoverColour(String colour);

}
