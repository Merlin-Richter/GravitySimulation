import java.awt.*;

public interface Entity {
    double get_x();
    double get_y();
    double get_m();
    void update(double dt);
    void render(Graphics2D g);
}