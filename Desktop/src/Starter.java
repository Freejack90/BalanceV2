import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import main.AndroidGame;

public class Starter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Balance";
        cfg.width = 480;
        cfg.height = 800;
        cfg.useGL20 = false;
        new LwjglApplication(new AndroidGame(), cfg);
        //Git
    }
}
