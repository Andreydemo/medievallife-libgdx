package com.demosoft.game.medievallife;

import org.springframework.stereotype.Component;

@Component
public class GlobalConfigs {

    private String blockSpritesPath;

    private int inMemoryMapBufSize = 100;

    public GlobalConfigs() {
    }

    public String getBlockSpritesPath() {
        return blockSpritesPath;
    }

    public void setBlockSpritesPath(String blockSpritesPath) {
        this.blockSpritesPath = blockSpritesPath;
    }

    public int getInMemoryMapBufSize() {
        return inMemoryMapBufSize;
    }

    public void setInMemoryMapBufSize(int inMemoryMapBufSize) {
        this.inMemoryMapBufSize = inMemoryMapBufSize;
    }
}
