package it.epicode.classes;

import java.util.Random;

public abstract class Catalog {
    private Random random = new Random();
    private long CodISBN;
    private String title;
    private int yearOfPublication;
    private int numberOfPages;

    public Catalog(String title, int yearOfPublication, int numberOfPages) {
        this.CodISBN = random.nextLong(100000, 999999);
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPages = numberOfPages;
    }

    public long getCodISBN() {
        return CodISBN;
    }

    public void setCodISBN(long codISBN) {
        CodISBN = codISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "CodISBN=" + CodISBN +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
