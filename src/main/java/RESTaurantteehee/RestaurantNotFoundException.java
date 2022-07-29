package RESTaurantteehee;

public class RestaurantNotFoundException extends RuntimeException {
    RestaurantNotFoundException(Long id){
        super("Could not find employee" + id);
    }
}
