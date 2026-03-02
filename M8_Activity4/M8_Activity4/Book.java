package M8_Activity4;

public class Book {
    private Long id;
    private String title;
    private String author;
    private Integer year;

    public Book() {}

    public Book(Long id, String title, String author, Integer year) {
        this.id = id; this.title = title; this.author = author; this.year = year;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getYear() { return year; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(Integer year) { this.year = year; }
}