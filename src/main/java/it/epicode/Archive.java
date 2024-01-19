package it.epicode;

import org.apache.commons.io.FileUtils;
import it.epicode.classes.Book;
import it.epicode.classes.Catalog;
import it.epicode.classes.Magazine;
import it.epicode.enums.Frequency;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        saveToDisk();

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

//        System.out.println("Che autore cerchi?");
//        String author = scanner.nextLine();
//        searchByAuthor(author);
    }

    //Crea un libro
    private static void addElement(String title, int yearOfPublication, int numberOfPages, String authorName, String genre) {
        Book book = new Book(title, yearOfPublication, numberOfPages, authorName, genre);
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

    private static void saveToDisk() {
        File file = new File("./src/output.txt");
        StringBuilder dataToWrite = new StringBuilder();

        for (Catalog catalogElement : catalogList) {
            StringBuilder typeData = new StringBuilder();
            if (catalogElement instanceof Book) {
                typeData.append(((Book) catalogElement).getAuthorName()).append("@")
                        .append(((Book) catalogElement).getGenre());
            }
            if (catalogElement instanceof Magazine) {
                typeData.append(((Magazine) catalogElement).getFrequency());
            }
            dataToWrite
                    .append(catalogElement.getCodISBN()).append("@")
                    .append(catalogElement.getTitle()).append("@")
                    .append(catalogElement.getYearOfPublication()).append("@")
                    .append(catalogElement.getNumberOfPages()).append("@")
                    .append(typeData).append("#");
        }
        try {
            FileUtils.writeStringToFile(file, String.valueOf(dataToWrite), StandardCharsets.UTF_8);
            System.out.println("Dati trascritti nel file output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
