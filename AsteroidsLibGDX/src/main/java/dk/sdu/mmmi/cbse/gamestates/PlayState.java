package dk.sdu.mmmi.cbse.gamestates;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dk.sdu.mmmi.cbse.entities.Enemy;
import dk.sdu.mmmi.cbse.entities.Player;
import dk.sdu.mmmi.cbse.managers.GameKeys;
import dk.sdu.mmmi.cbse.managers.GameStateManager;

public class PlayState extends GameState {
	
	private ShapeRenderer sr;
	
	private Player player;
	private Enemy enemy;
	private float fsTimer;
	private float fsTime;
	public PlayState(GameStateManager gsm) {
		super(gsm);
	}
	
	public void init() {
		
		sr = new ShapeRenderer();
		
		player = new Player();
		enemy = new Enemy(1, player);
		
	}
	
	public void update(float dt) {
		
		handleInput();
		
		player.update(dt);
		enemy.update(dt);

		if(enemy == null) {
			fsTimer += dt;
			if(fsTimer >= fsTime) {
				fsTimer = 0;
				int direction = MathUtils.random() < 0.5 ?
						enemy.RIGHT : enemy.LEFT;
				enemy = new Enemy(
						direction,
						player
				);
			}
		}
		
	}
	
	public void draw() {
		player.draw(sr);
		if(enemy != null) {
			enemy.draw(sr);
		}
	}
	
	public void handleInput() {
		player.setLeft(GameKeys.isDown(GameKeys.LEFT));
		player.setRight(GameKeys.isDown(GameKeys.RIGHT));
		player.setUp(GameKeys.isDown(GameKeys.UP));
	}
	
	public void dispose() {}
	
}









