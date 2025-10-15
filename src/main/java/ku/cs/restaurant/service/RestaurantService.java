package ku.cs.restaurant.service;

import ku.cs.restaurant.entity.Restaurant;
import ku.cs.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    public RestaurantService(RestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    public Restaurant getRestaurantById(UUID id) {
        return repository.findById(id).get();
    }

    public Restaurant create(Restaurant restaurant) {
        restaurant.setCreatedAt(Instant.now());
        return repository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        UUID id = restaurant.getId();
        Restaurant record = repository.findById(id).get();
        record.setName(restaurant.getName());
        record.setRating(restaurant.getRating());
        record.setLocation(restaurant.getLocation());

        record = repository.save(record);
        return record;
    }
}
