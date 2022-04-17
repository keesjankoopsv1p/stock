package nl.hu.bep3.kees.stock.infrastructure.driven.storage;

import nl.hu.bep3.kees.stock.core.port.storage.MealRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpMealRepository implements MealRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpMealRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public List<String> getIngredientsByMeal(String id) {
        URI uri = URI.create(this.rootPath + "/meals/" + id + "/ingredients");
        List<String> result = this.client.getForObject(uri, ArrayList.class);

        if (result == null) {
            return new ArrayList<>();
        }
        return result;
    }
}
