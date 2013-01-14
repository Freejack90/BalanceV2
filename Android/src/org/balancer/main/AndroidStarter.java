package org.balancer.main;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import main.AndroidGame;

/**
 * Created with IntelliJ IDEA.
 * User: Freejack
 * Date: 14.01.13
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public class AndroidStarter extends AndroidApplication {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize(new AndroidGame(), false);
    }
}
