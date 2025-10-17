package ku.cs.restaurant.service;

import ku.cs.restaurant.dto.RestaurantRequest;
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

    public Restaurant getRestaurantByName(String name) {
        return repository.findByName(name);
    }

    public List<Restaurant> getRestaurantByLocation(String location) {
        return repository.findByLocation(location);
    }

    public Restaurant create(RestaurantRequest request) {
        Restaurant dao = new Restaurant();
        dao.setName(request.getName());
        dao.setRating(request.getRating());
        dao.setLocation(request.getLocation());

        dao.setCreatedAt(  Instant.now()  );
        return repository.save(dao);
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

    public Restaurant delete(UUID id) {
        Restaurant record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }
}
