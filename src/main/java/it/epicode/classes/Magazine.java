package it.epicode.classes;

import it.epicode.baseClasses.Catalog;
import it.epicode.enums.Frequency;

public class Magazine extends Catalog {
    private Frequency frequency;

    public Magazine(String title, int yearOfPublication, int numberOfPages, Frequency frequency) {
        super(title, yearOfPublication, numberOfPages);
        this.frequency = frequency;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                " CodISBN=" + CodISBN +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPages=" + numberOfPages +
                ", frequency=" + frequency +
                '}';
    }
}
