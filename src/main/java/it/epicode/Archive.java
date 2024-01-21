package it.epicode;

import it.epicode.baseClasses.Catalog;
import it.epicode.classes.Book;
import it.epicode.classes.Magazine;
import it.epicode.enums.Frequency;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static it.epicode.utils.Utils.createCatalog;

public class Archive {
    static List<Catalog> catalogList = new ArrayList<>();

    public static void main(String[] args) {

        int numberOfElement = 3; // Crea n Book e n Magazine
        createCatalog(numberOfElement, catalogList);
        catalogList.forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);

        //Test metodi

        System.out.println("Che elemento vuoi eliminare?");
        long id = Long.parseLong(scanner.nextLine());
        removeElement(id);

        catalogList.forEach(System.out::println);

        System.out.println("Che elemento vuoi cercare? (Ricerca tramite codice ISBN)");
        long id2 = Long.parseLong(scanner.nextLine());
        searchByISBN(id2);

        System.out.println("Che anno vuoi cercare?");
        int year = Integer.parseInt(scanner.nextLine());
        searchByYearOfPublication(year);

        System.out.println("Che autore cerchi?");
        String author = scanner.nextLine();
        searchByAuthor(author);

        saveToDisk();
        findToDisk();
        catalogList.forEach(System.out::println);

        scanner.close();
    }

    //Crea un libro
    private static Book addElement(String title, int yearOfPublication, int numberOfPages, String authorName, String genre) {
        Book book = new Book(title, yearOfPublication, numberOfPages, authorName, genre);
        catalogList.add(book);
        return book;
    }

    //Crea un magazine con overload del metodo
    private static Magazine addElement(String title, int yearOfPublication, int numberOfPages, Frequency frequency) {
        Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, frequency);
        catalogList.add(magazine);
        return magazine;
    }

    //Rimozione di un elemento dato un codice ISBN
    private static void removeElement(long ISBN) {
        boolean removed = catalogList.removeIf(catalogElement -> catalogElement.getCodISBN() == ISBN);
        String response = removed ? "Elemento rimosso" : "Elemento non trovato";
        System.out.println(response);
    }

    //Ricerca per ISBN
    private static void searchByISBN(long ISBN) {
        List<Catalog> ISBNResearch = catalogList.stream()
                .filter(catalogElement -> catalogElement.getCodISBN() == ISBN)
                .toList();

        if (ISBNResearch.isEmpty()) {
            System.out.println("Non esiste elemento nel catalogo con questo codice ISBN");
        } else {
            ISBNResearch.forEach(System.out::println);
        }
    }

    //Ricerca per anno di pubblicazione
    private static void searchByYearOfPublication(int yearOfPublication) {
        List<Catalog> yearResearch = catalogList.stream()
                .filter(catalogElement -> catalogElement.getYearOfPublication() == yearOfPublication)
                .toList();

        if (yearResearch.isEmpty()) {
            System.out.println("Non esiste nessun libro pubblicato in questo anno");
        } else {
            yearResearch.forEach(System.out::println);
        }
    }

    //Ricerca per autore
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

        //Ho preferito l'utilizzo dello StringBuilder che mi sembra più leggibile invece della concatenazione delle stringhe
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
            FileUtils.writeStringToFile(file, dataToWrite + System.lineSeparator(), StandardCharsets.UTF_8, true);
            System.out.println("Dati trascritti nel file output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void findToDisk() {
        catalogList.clear();
        File file = new File("./src/output.txt");

        try {
            String fileString = FileUtils.readFileToString(file, "UTF-8");
            List<String> splitFileString = Arrays.asList(fileString.split("#"));

            splitFileString.forEach(stringa -> {
                String[] fileElement = stringa.split("@");
                if (fileElement.length == 5) {
                    Catalog magazine = addElement(fileElement[1], Integer.parseInt(fileElement[2]), Integer.parseInt(fileElement[3]), Frequency.valueOf(fileElement[4]));
                    magazine.setCodISBN(Long.parseLong(fileElement[0]));
                } else if (fileElement.length == 6) {
                    Catalog book = addElement(fileElement[1], Integer.parseInt(fileElement[2]), Integer.parseInt(fileElement[3]), fileElement[4], fileElement[5]);
                    book.setCodISBN(Long.parseLong(fileElement[0]));
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
