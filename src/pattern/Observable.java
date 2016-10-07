package pattern;

import Entity.Food;

import java.util.List;

/**
 * Created by alex on 07/10/2016.
 */
public interface Observable {
    public void newFood(List<Food> foodList);
}
