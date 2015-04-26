package com.demosoft.game.medievallife;

import org.springframework.stereotype.Component;

@Component
public class GlobalConfigs {

	private String blockSpritesPath;
	
	public GlobalConfigs() {
		System.out.println("globalConfigs inited");
	}

	public String getBlockSpritesPath() {
		return blockSpritesPath;
	}

	public void setBlockSpritesPath(String blockSpritesPath) {
		this.blockSpritesPath = blockSpritesPath;
	}

}
