package com.demosoft.game.medievallife;

import org.springframework.stereotype.Component;

@Component
public class GlobalConfigs {

	private String blockSpritesPath;
	
	public GlobalConfigs() {
	}

	public String getBlockSpritesPath() {
		return blockSpritesPath;
	}

	public void setBlockSpritesPath(String blockSpritesPath) {
		this.blockSpritesPath = blockSpritesPath;
	}

}
