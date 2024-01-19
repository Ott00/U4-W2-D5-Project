package it.epicode.classes;

public class Book extends Catalog {
    private String authorName;
    private String genre;


    public Book(String title, int yearOfPublication, int numberOfPages, String authorName, String genre) {
        super(title, yearOfPublication, numberOfPages);
        this.authorName = authorName;
        this.genre = genre;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "CodISBN=" + super.getCodISBN() +
                ", title='" + super.getTitle() + '\'' +
                ", yearOfPublication=" + super.getYearOfPublication() +
                ", numberOfPages=" + super.getNumberOfPages() +
                ", authorName='" + authorName + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
