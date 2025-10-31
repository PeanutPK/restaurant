package ku.cs.restaurant.repository;

import ku.cs.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
    boolean existsByName(String name);
    Optional<Restaurant> findByName(String name);
    List<Restaurant> findByLocation (String location);
}
