import java.awt.*;

public interface Entity {
    int get_x();
    int get_y();
    int get_m();
    void update(double dt);
    void render(Graphics2D g);
}