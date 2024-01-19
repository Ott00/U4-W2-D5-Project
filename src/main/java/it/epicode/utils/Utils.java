package it.epicode.utils;

import com.github.javafaker.Faker;
import it.epicode.classes.Book;
import it.epicode.baseClasses.Catalog;
import it.epicode.classes.Magazine;
import it.epicode.enums.Frequency;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.function.Supplier;

public class Utils {
    public static Frequency getRandomEnum(Class<Frequency> enumeration) {
        Random random = new Random();
        Frequency[] values = enumeration.getEnumConstants();
        return values[random.nextInt(values.length)];
    }

    public static void createCatalog(int numberOfElement, List<Catalog> catalogList) {
        Faker faker = new Faker(Locale.ITALY);

        Supplier<Book> bookSupplier = () -> new Book(
                faker.book().title(),
                faker.number().numberBetween(2020, 2024),
                faker.number().numberBetween(50, 1000),
                faker.name().firstName(),
                faker.book().genre());

        Supplier<Magazine> magazineSupplier = () -> new Magazine(
                faker.book().title(),
                faker.number().numberBetween(2020, 2024),
                faker.number().numberBetween(50, 1000),
                getRandomEnum(Frequency.class)
        );

        for (int i = 0; i < numberOfElement; i++) {
            catalogList.add(bookSupplier.get());
        }

        for (int i = 0; i < numberOfElement; i++) {
            catalogList.add(magazineSupplier.get());
        }

    }


}
