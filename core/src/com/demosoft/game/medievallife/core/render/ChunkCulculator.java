package com.demosoft.game.medievallife.core.render;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector3;
import com.demosoft.game.medievallife.core.IsometricCamera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.demosoft.game.medievallife.ContextConteiner;
import com.demosoft.game.medievallife.core.AbstractGameObject;
import com.demosoft.game.medievallife.core.Chunk;

@Component
public class ChunkCulculator {

    int height = Gdx.graphics.getHeight();
    int width = Gdx.graphics.getWidth();
    private Vector2 sizeInWorld;

    private static Chunk mainChunk;

    @Autowired
    private ContextConteiner context;

    public Chunk calculateFirstChunk(int width, int height) {
        float diagonalLength = new Double(((2 * height + width) / 4) * Math.sqrt(5)).floatValue();
        float chunkWidth = new Double(diagonalLength
                / (Math.sqrt(AbstractGameObject.SCREEN_HEIGHT2 * AbstractGameObject.SCREEN_HEIGHT2
                + AbstractGameObject.SCREEN_WIDTH2 * AbstractGameObject.SCREEN_WIDTH2))).floatValue();
        int chunkWidthI = (int) Math.ceil(chunkWidth);
        Chunk retChunk = new Chunk(new Vector2(0, 0), chunkWidthI, chunkWidthI);
        sizeInWorld = retChunk.getSizeInWorld();
        retChunk.setFirstPointIn(calculatesetFirstPointInWorld(retChunk.getStartPostion()));
        mainChunk = retChunk;
        return retChunk;
    }

    public Chunk calculateMainChunk(Camera camera) {
        float diagonalLength = new Double(((2 * height + width) / 4) * Math.sqrt(5)).floatValue();
        float chunkWidth = new Double(diagonalLength
                / (Math.sqrt(AbstractGameObject.SCREEN_HEIGHT2 * AbstractGameObject.SCREEN_HEIGHT2
                + AbstractGameObject.SCREEN_WIDTH2 * AbstractGameObject.SCREEN_WIDTH2))).floatValue();
        int chunkWidthI = (int) Math.ceil(chunkWidth);
        Chunk retChunk = new Chunk(new Vector2(0, 0), chunkWidthI, chunkWidthI);
        sizeInWorld = retChunk.getSizeInWorld();
        //  retChunk.setFirstPointIn(calculatesetFirstPointInWorld(camera, retChunk.getStartPostion()));
        Vector3 v = IsometricCamera.getGridToWorld(new Vector3(0, 0, 0));
        retChunk.setFirstPointIn(new Vector2(v.x, v.y));
        mainChunk = retChunk;
        return retChunk;
    }

    private void recalculateMainChank() {
        //mainChunk.setFirstPointIn(calculatesetFirstPointInWorld(context.getCamera(), mainChunk.getStartPostion()));
    }

    public void processChunks() {
        if (mainChunk != null) {
            recalculateMainChank();
        }
    }

    private Vector2 calculatesetFirstPointInWorld(Vector2 vector2) {
        Vector2 diff = new Vector2(vector2.y * sizeInWorld.x / 2 + vector2.x * sizeInWorld.x / 2, vector2.y
                * sizeInWorld.y / 2 + -1 * vector2.x * sizeInWorld.y / 2);
        Vector2 startPos = new Vector2(-1 * height + diff.x, height / 2 + diff.y);
        return startPos;
    }

    private Vector2 calculatesetFirstPointInWorld(Camera camera, Vector2 vector2) {
        Vector2 cameraDiff = new Vector2(width / 2 - camera.position.x, height / 2 - camera.position.y);
        Vector2 diff = new Vector2(vector2.y * sizeInWorld.x / 2 + vector2.x * sizeInWorld.x / 2, vector2.y
                * sizeInWorld.y / 2 + -1 * vector2.x * sizeInWorld.y / 2);
        Vector2 startPos = new Vector2(-1 * height + diff.x - cameraDiff.x, height / 2 + diff.y - cameraDiff.y);
        return startPos;
    }

    @Deprecated
    public List<Chunk> generateChunks(Chunk mainChunk) {
        List<Chunk> newChunks = new ArrayList<Chunk>(8);
        Chunk chunk;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
        /*
         * chunk = new Chunk(i, j, mainChunk.getHeight(),
		 * mainChunk.getWidth());
		 * chunk.setFirstPointIn(calculatesetFirstPointInWorld(chunk));
		 * newChunks.add(chunk);
		 */
            }
        }
        return newChunks;
    }


    public static void main(String[] args) {
        ChunkCulculator ChunkCulculator = new ChunkCulculator();
	/*
	 * System.out.println(ChunkCulculator.calculateFirstChunk(800, 600));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1024, 600));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1024, 768));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1280, 720));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1280, 768));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1280, 1024));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1440, 900));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1600, 900));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1600, 1200));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1920, 1200));
	 * System.out.println(ChunkCulculator.calculateFirstChunk(1920, 1080));
	 */
        ChunkCulculator.width = 1920;
        ChunkCulculator.height = 1080;
        Chunk chunk = ChunkCulculator.calculateFirstChunk(1920, 1080);
        System.out.println(chunk);
        System.out.println(chunk.getSizeInWorld());
        System.out.println(ChunkCulculator.calculatesetFirstPointInWorld(new Vector2(-1, 1)));
    }

}
