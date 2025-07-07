


import java.awt.*;
import java.util.List;

public  class Planet implements Entity {

    double x, y, vx, vy, ax, ay, m, r;
        Color color;
        public Planet (double x , double y , double vx, double vy, double m, double r, Color color){
            this.x = x; this.y = y ; this.vx = vx; this.vy = vy; this.m = m; this.r = r; this.color = color;
        }

    @Override
    public double get_x() {
        return this.x;
    }

    @Override
    public double get_m() {
        return this.m;
    }

    @Override
    public double get_y() {
        return this.y;
    }

    public void update(double dt, List<Entity> other_entities) {
            vx += ax * dt;
            vy += ay * dt ;
            x+= vx * dt;
            y += vy * dt;
        }

        public void force_calc (Entity other){
            double G = 1;
            double FGx = 0;
            double FGy = 0;
            double FG = 0;
            double x_distance = other.get_x() - x;
            double y_distance = other.get_y() - y;
            double r2 = Math.pow(x_distance,2) + Math.pow(y_distance,2);
            double r = Math.sqrt(r2);
            if (r2 == 0) return;
            FG = G * (m * other.get_m() )/ r2;
            FGx = FG * x_distance/r;
            FGy = FG * y_distance/r;
            ax += FGx/m;
            ay += FGy/m;
        }


        public void render(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int)(x - r), (int)(y - r),  (int)r * 2, (int)r * 2);
    }
    }

