package it.epicode;

import it.epicode.classes.Book;
import it.epicode.classes.Catalog;
import it.epicode.classes.Magazine;
import it.epicode.enums.Frequency;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static it.epicode.utils.Utils.createCatalog;

public class Archive {
    static List<Catalog> catalogList = new ArrayList<>();

    public static void main(String[] args) {
        int numberOfElement = 5;
        createCatalog(numberOfElement, catalogList);
        catalogList.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Che elemento vuoi eliminare?");
//        long id = Long.parseLong(scanner.nextLine());
//        removeElement(id);
//
//        catalogList.forEach(System.out::println);

//        System.out.println("Che elemento vuoi cercare?");
//        long id2 = Long.parseLong(scanner.nextLine());
//        searchByISBN(id2);

//        System.out.println("Che anno vuoi cercare?");
//        int year = Integer.parseInt(scanner.nextLine());
//        searchByYearOfPublication(year);

        System.out.println("Che autore cerchi?");
        String author = scanner.nextLine();
        searchByAuthor(author);
    }

    //Crea un libro
    private static void addElement(String title, int yearOfPublication, int numberOfPages, String authorName, String authorSurname, String genre) {
        Book book = new Book(title, yearOfPublication, numberOfPages, authorName, authorSurname, genre);
        catalogList.add(book);
    }

    //Crea un magazine
    private static void addElement(String title, int yearOfPublication, int numberOfPages, Frequency frequency) {
        Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, frequency);
        catalogList.add(magazine);
    }

    //Rimozione di un elemento dato un codice ISBN
    private static void removeElement(long ISBN) {
        boolean removed = catalogList.removeIf(catalogElement -> catalogElement.getCodISBN() == ISBN);
        String response = removed ? "Elemento rimosso" : "Elemento non trovato";
        System.out.println(response);
    }

    //Ricerca per ISBN
    private static void searchByISBN(long ISBN) {
        catalogList.stream()
                .filter(catalogElement -> catalogElement.getCodISBN() == ISBN)
                .forEach(System.out::println);
    }

    //Ricerca per anno di pubblicazione
    private static void searchByYearOfPublication(int yearOfPublication) {
        catalogList.stream()
                .filter(catalogElement -> catalogElement.getYearOfPublication() == yearOfPublication)
                .forEach(System.out::println);
    }

    private static void searchByAuthor(String author) {
        List<Catalog> authorBooks = catalogList.stream()
                .filter(catalog -> catalog instanceof Book)
                .filter(book -> ((Book) book).getAuthorName().equals(author))
                .toList();

        if (authorBooks.isEmpty()) {
            System.out.println("Non esiste questo autore");
        } else {
            authorBooks.forEach(System.out::println);
        }
    }
}
