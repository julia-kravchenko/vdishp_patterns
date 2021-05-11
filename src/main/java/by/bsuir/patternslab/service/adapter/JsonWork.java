package by.bsuir.patternslab.service.adapter;

import by.bsuir.patternslab.entity.Publication;
import by.bsuir.patternslab.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class JsonWork {
    public void printPublicationsInJsonFormat() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        // System.out.println(gson.toJson(users));
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(new File
                    (Objects.requireNonNull(JsonWork.class.getClassLoader().getResource("publications.json")).toURI())));
        } catch (FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }
        Publication[] publications = gson.fromJson(reader, Publication[].class);
        List<Publication> list = Arrays.asList(publications);
        System.out.println(gson.toJson(list));
    }
}
