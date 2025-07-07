import java.awt.*;
import java.util.List;
public interface Entity {
    double get_x();
    double get_y();
    double get_m();
    void update(double dt, List<Entity> other_entities);
    void render(Graphics2D g);
}