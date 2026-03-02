package M8_Activity10;

public class BookDTO {
    private String title;
    private String author;

    public BookDTO() {}
    public BookDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    public void setTitle(String t) { this.title = t; }
    public void setAuthor(String a) { this.author = a; }
}
