package com.demosoft.game.medievallife;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.math.Vector2;
import com.demosoft.game.medievallife.core.AbstractGameObject;

@Component
public class ChunkCulculator {

	int height = 1080;
	int width = 1920;

	private static Chunk mainChunk;

	public Chunk calculateFirstChunk(int width, int height) {
		float diagonalLength = new Double(((2 * height + width) / 4) * Math.sqrt(5)).floatValue();
		Vector2 startPos = new Vector2(-1 * height, height / 2);
		float chunkWidth = new Double(diagonalLength
				/ (Math.sqrt(AbstractGameObject.SCREEN_HEIGHT2 * AbstractGameObject.SCREEN_HEIGHT2 + AbstractGameObject.SCREEN_WIDTH2 * AbstractGameObject.SCREEN_WIDTH2)))
				.floatValue();
		int chunkWidthI = (int) Math.ceil(chunkWidth);
		Chunk retChunk = new Chunk(0, 0, chunkWidthI, chunkWidthI);
		retChunk.setFirstPointIn(startPos);
		mainChunk = retChunk;
		return retChunk;
	}

	public void processChunks() {
		if (mainChunk != null) {
			generateChunks(mainChunk);
		}
	}
	
	/*private Vector2 calculatesetFirstPointInWorld(int x,int y){ 
		Vector2 startPos = new Vector2(-1 * height, height / 2);
		return null;
	}*/

	public List<Chunk> generateChunks(Chunk mainChunk) {
		List<Chunk> newChunks = new ArrayList<Chunk>(8);
		Chunk chunk = new Chunk(mainChunk.getX(), mainChunk.getY() - mainChunk.getHeight(), mainChunk.getHeight(), mainChunk.getWidth());
		//Chunk chunk = new Chunk(mainChunk.getX(), mainChunk.getY() - mainChunk.getHeight(), mainChunk.getHeight(), mainChunk.getWidth());
		//Chunk chunk = new Chunk(mainChunk.getX(), mainChunk.getY() - mainChunk.getHeight(), mainChunk.getHeight(), mainChunk.getWidth());
		return null;
	}

	public static void main(String[] args) {
		ChunkCulculator ChunkCulculator = new ChunkCulculator();
		System.out.println(ChunkCulculator.calculateFirstChunk(800, 600));
		System.out.println(ChunkCulculator.calculateFirstChunk(1024, 600));
		System.out.println(ChunkCulculator.calculateFirstChunk(1024, 768));
		System.out.println(ChunkCulculator.calculateFirstChunk(1280, 720));
		System.out.println(ChunkCulculator.calculateFirstChunk(1280, 768));
		System.out.println(ChunkCulculator.calculateFirstChunk(1280, 1024));
		System.out.println(ChunkCulculator.calculateFirstChunk(1440, 900));
		System.out.println(ChunkCulculator.calculateFirstChunk(1600, 900));
		System.out.println(ChunkCulculator.calculateFirstChunk(1600, 1200));
		System.out.println(ChunkCulculator.calculateFirstChunk(1920, 1200));
		System.out.println(ChunkCulculator.calculateFirstChunk(1920, 1080));
		Chunk chunk = ChunkCulculator.calculateFirstChunk(1920, 1080);
		System.out.println(chunk);
	}

}
