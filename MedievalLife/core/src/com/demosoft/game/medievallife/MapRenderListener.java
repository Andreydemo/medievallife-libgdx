package com.demosoft.game.medievallife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demosoft.game.medievallife.core.AbstractGameObject;
import com.demosoft.game.medievallife.core.Map;
import com.demosoft.game.medievallife.core.RenderListener;


@Component
public class MapRenderListener extends RenderListener {

	@Autowired
	private Map map;
	
	@Autowired
	private ContextConteiner context;
	
	@Override
	public void render() {
		AbstractGameObject[][] map =  this.map.getMapObj()[0];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j]!= null){ 
					//System.out.println(map[i][j].getPosition());
				}
			}
		}
	}

}
