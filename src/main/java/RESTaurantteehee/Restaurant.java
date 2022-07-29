package RESTaurantteehee;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Restaurant {
    private @Id @GeneratedValue Long id;
    private String name;
    private String type;
    private String cost;
    private Long rating;

    Restaurant(){}

    Restaurant(String name, String type, String cost, Long rating){
        this.name = name;
        this.type = type;
        this.cost = cost;
        this.rating = rating;
    }

    public Long getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getType(){
        return this.type;
    }
    public String getCost() {
        return this.cost;
    }
    public Long getRating(){
        return this.rating;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(String cost){
        this.cost = cost;
    }

    public void setRating(Long rating){
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof Restaurant))
            return false;
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(this.id, restaurant.id) && Objects.equals(this.name, restaurant.name)
                && Objects.equals(this.type, restaurant.type) && Objects.equals(this.cost, restaurant.cost)
                && Objects.equals(this.rating, restaurant.rating);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.type, this.cost, this.rating);
    }

    @Override
    public String toString() {
        return "Restaurant{" + "id=" + this.id + ", name='" + this.name + '\'' + ", type='"
                + this.type + '\'' + ", cost='" + this.cost + '\'' + ", rating='" + this.rating + '\'' + '}';
    }
}

