package Entity;

import java.awt.*;

/**
 * Created by alex on 05/10/2016.
 */
public class RottenFood extends Food {
    public RottenFood(int x, int y) {
        super(x,y, Toolkit.getDefaultToolkit().getImage("./ressource/sprite/Rottenmeat.png"));
    }
}
