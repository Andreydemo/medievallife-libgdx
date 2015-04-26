package com.demosoft.game.medievallife.core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.math.Vector3;

@Component
public class Map {

	@Autowired
	GameObjectFactory factory;
	private AbstractGameObject[][][] mapObj;// [z][x][y]

	public Map() {

	}

	@PostConstruct
	public void init() {
		mapObj = new AbstractGameObject[1][][];
		mapObj[0] = new AbstractGameObject[10][10];
		for (int i = 0; i < mapObj[0].length; i++) {
			for (int j = 0; j < mapObj[0][0].length; j++) {
				if (i % 2 == 0 && j % 2 == 1 ) {
					mapObj[0][i][j] = factory.getDirtBlock();
					mapObj[0][i][j].setPosition(new Vector3(i, j, 0));
				} else {
					mapObj[0][i][j] = null;
				}
			}
		}
	}

	public AbstractGameObject[][][] getMapObj() {
		return mapObj;
	}

	public void setMapObj(AbstractGameObject[][][] mapObj) {
		this.mapObj = mapObj;
	}
}
