package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("Asteroids");
		cfg.setResizable(false);
		cfg.setWindowSizeLimits(900, 500, 900, 500);
		cfg.setWindowedMode(900, 500);


		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfigure.class);

		new Lwjgl3Application(ctx.getBean(Game.class), cfg);
	}

}