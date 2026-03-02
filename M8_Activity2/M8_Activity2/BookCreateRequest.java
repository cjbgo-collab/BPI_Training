package M8_Activity2;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookCreateRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private Integer year;

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getYear() { return year; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(Integer year) { this.year = year; }
}