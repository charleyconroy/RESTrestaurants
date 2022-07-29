package RESTaurantteehee;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component class RestaurantModelAssembler implements RepresentationModelAssembler<Restaurant,EntityModel<Restaurant>>{
    @Override
    public EntityModel<Restaurant> toModel(Restaurant restaurant){
        return EntityModel.of(restaurant,
                linkTo(methodOn(RestaurantController.class).one(restaurant.getId())).withSelfRel(),
                linkTo(methodOn(RestaurantController.class).all()).withRel("restaurants"));
    }

}
