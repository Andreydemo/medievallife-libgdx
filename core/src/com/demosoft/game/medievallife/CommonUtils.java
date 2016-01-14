package com.demosoft.game.medievallife;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by demos_000 on 14.01.2016.
 */
public class CommonUtils {

    public static Vector2 convert(Vector3 vector3) {
        return new Vector2(vector3.x, vector3.y);
    }

    public static Vector3 convert(Vector2 vector2) {
        return new Vector3(vector2.x, vector2.y, 0f);
    }
}
