package teamWork2.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import teamWork2.entity.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadingFromJson {
    public List<Product> convertJsonToList(String filePath) throws IOException {
        //1.nuskaitom json faila ir patalpinam i eilute
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
        //System.out.println(jsonString);
        //2. konvertuojam nuskaityta eilute i saras.
        List<Product> products = new ObjectMapper().readValue(jsonString, new TypeReference<>() {
        });
        //3. graziname turima sarasa.
        return products;
    }
}
