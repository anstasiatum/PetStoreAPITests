package tests;

import com.github.javafaker.Faker;
import models.petmodel.TagModel;

import java.util.List;

public class TestData {
    private static final Faker faker = new Faker();
    public static Long categoryID = faker.random().nextLong(10_000);
    public static String categoryName = faker.animal().name();
    public static String petName = faker.funnyName().name();
    public static String photoUrl = faker.internet().url();
    public static Long tagID = faker.random().nextLong(10_000);
    public static String tagName = faker.color().name();
    public static List<TagModel> tags;
    public static String status = faker.options().option("available", "pending", "sold");
    public static String soldStatus = "sold";
    public static String availableStatus = "available";
}
