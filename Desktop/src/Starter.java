import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import main.AndroidGame;

/**
 * Created with IntelliJ IDEA.
 * User: Freejack
 * Date: 14.01.13
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */
public class Starter {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Balance";
        cfg.width = 480;
        cfg.height = 800;
        cfg.useGL20 = false;
        new LwjglApplication(new AndroidGame(), cfg);
    }
}
