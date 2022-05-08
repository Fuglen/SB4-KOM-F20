package dk.sdu.mmmi.cbse.entities;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.main.Game;
//import sun.jvm.hotspot.gc.shared.Space;

public class Enemy extends SpaceObject {
    private boolean left;
    private boolean right;
    private boolean up;

    private float maxSpeed;
    private float acceleration;
    private float deceleration;

    private float pathTimer;
    private float pathTime1;
    private float pathTime2;

    private int direction;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Enemy(int direction, Player player) {

        x = Game.WIDTH / 2;
        y = Game.HEIGHT / 2;

        maxSpeed = 300;
        acceleration = 200;
        deceleration = 10;

        shapex = new float[6];
        shapey = new float[6];

        radians = 3.1415f / 2;
        rotationSpeed = 3;

        speed = 70;
        if(direction == LEFT) {
            dx = -speed;
            x = Game.WIDTH;
        }
        else if(direction == RIGHT) {
            dx = speed;
            x = 0;
        }
        y = MathUtils.random(Game.HEIGHT);

    }

    private void setShape() {
        shapex[0] = x - 10;
        shapey[0] = y;

        shapex[1] = x - 3;
        shapey[1] = y - 5;

        shapex[2] = x + 3;
        shapey[2] = y - 5;

        shapex[3] = x + 10;
        shapey[3] = y;

        shapex[4] = x + 3;
        shapey[4] = y + 5;

        shapex[5] = x - 3;
        shapey[5] = y + 5;
    }

    public void update(float dt) {

        Bullet bullet = new Bullet()
        pathTimer += dt;

        // move forward
        if(pathTimer < pathTime1) {
            dy = 0;
        }

        // move downward
        if(pathTimer > pathTime1 && pathTimer < pathTime2) {
            dy = -speed;
        }

        // move to end of screen
        if(pathTimer > pathTime1 + pathTime2) {
            dy = 0;
        }

        x += dx * dt;
        y += dy * dt;

        // screen wrap
        if(y < 0) y = Game.HEIGHT;

        // set shape
        setShape();

        // screen wrap
        wrap();

    }

    public void draw(ShapeRenderer sr) {

        sr.setColor(1, 1, 1, 1);

        sr.begin(ShapeRenderer.ShapeType.Line);

        for (int i = 0, j = shapex.length - 1;
             i < shapex.length;
             j = i++) {

            sr.line(shapex[i], shapey[i], shapex[j], shapey[j]);

        }

        sr.end();

    }
}
