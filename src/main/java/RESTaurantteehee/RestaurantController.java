package RESTaurantteehee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
class RestaurantController {
    private final RestaurantRepository repository;
    private final RestaurantModelAssembler assembler;
    RestaurantController(RestaurantRepository repository, RestaurantModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/restaurants")
    CollectionModel<EntityModel<Restaurant>> all() {
        List<EntityModel<Restaurant>> restaurants = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(restaurants, linkTo(methodOn(RestaurantController.class).all()).withSelfRel());
    }

    @PostMapping("/restaurants")
    ResponseEntity<EntityModel<Restaurant>> newRestaurant(@RequestBody Restaurant newRestaurant){
        EntityModel<Restaurant> entityModel = assembler.toModel(repository.save(newRestaurant));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/restaurants/{id}")
    EntityModel<Restaurant> one(@PathVariable Long id){
        Restaurant restaurant = repository.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        return assembler.toModel(restaurant);
    }

    @PutMapping("/restaurants/{id}")
    ResponseEntity<EntityModel<Restaurant>> replaceRestaurant(@RequestBody Restaurant newRestaurant, @PathVariable Long id){
        Restaurant updatedRestaurant = repository.findById(id)
                .map(restaurant -> {
                    restaurant.setName(newRestaurant.getName());
                    restaurant.setType(newRestaurant.getType());
                    restaurant.setCost(newRestaurant.getCost());
                    restaurant.setRating(newRestaurant.getRating());
                    return repository.save(restaurant);
                })
                .orElseGet(()-> {
                    newRestaurant.setId(id);
                    return repository.save(newRestaurant);
                });
        EntityModel<Restaurant> entityModel = assembler.toModel(updatedRestaurant);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @DeleteMapping("/restaurants/{id}")
    ResponseEntity<?> deleteRestaurant(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
