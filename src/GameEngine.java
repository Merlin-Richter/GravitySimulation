import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 * Minimal but scalable 2-D engine demo.
 * Compile:  javac GameEngine.java
 * Run:      java  GameEngine
 */
public class GameEngine extends Canvas implements Runnable {

    /* ---------- entry point ---------- */
    public static void main(String[] args) {
        JFrame frame = new JFrame("MVP 2-D Engine");
        GameEngine engine = new GameEngine();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(engine);
        frame.pack();                     // sizes the frame to Canvas’ pref. size
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        engine.start();
    }

    /* ---------- engine core ---------- */
    private volatile boolean running;
    private Thread loopThread;
    private final List<Entity> entities = new ArrayList<>();

    private GameEngine() {
        setPreferredSize(new Dimension(800, 600));
        entities.add(new Planet(400, 300, 10, 20, 50, 30, Color.RED));
        entities.add(new Planet(200, 100, 0, 0, 50, 30, Color.RED));
    }

    private void start() {
        if (running) return;
        running = true;
        loopThread = new Thread(this, "GameLoop");
        loopThread.start();
    }

    @Override public void run() {
        createBufferStrategy(3);                 // triple buffering
        BufferStrategy bs = getBufferStrategy();

        final double nsPerUpdate = 1_000_000_000.0 / 60; // 60 Hz
        long last = System.nanoTime();
        double delta = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - last) / nsPerUpdate;
            last = now;

            while (delta >= 1) { update(nsPerUpdate/1000000000); delta--; }
            render(bs);
        }
    }

    private void update(double dt) {

        for (Entity e : entities){
            e.update(dt, entities);
        }
    }

    private void render(BufferStrategy bs) {
        do {
            do {
                Graphics2D g = (Graphics2D) bs.getDrawGraphics();
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());

                for (Entity e : entities) e.render(g);
                g.dispose();
            } while (bs.contentsRestored());
            bs.show();
        } while (bs.contentsLost());
    }


}