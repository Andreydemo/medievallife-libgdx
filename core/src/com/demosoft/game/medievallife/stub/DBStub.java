package com.demosoft.game.medievallife.stub;

public class DBStub {

    private static Excel[][] map = new Excel[100][100];
    
    public static Excel startPlayerPos = new Excel();

    static {
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[i].length; j++) {
		map[i][j] = new Excel();
		map[i][j].setValue(0);
		map[i][j].setPostion(i - 10, j - 10);
	    }
	}
	startPlayerPos.setPostion(25, 25);
    }

    public static Excel getByIndex(int x, int y) {
	return map[x][y];
    }

    public static Excel[][] getRange(int startX, int startY, int xLenght, int yLenght) {
	Excel[][] retMap = new Excel[xLenght][yLenght];
	for (int i = 0; i < retMap.length; i++) {
	    for (int j = 0; j < retMap[i].length; j++) {
		retMap[i][j] = map[i + startX + 10][j + startY + 10];
	    }
	}
	return retMap;

    }

    public static void outMap(Excel[][] map){ 
	for (int i = 0; i < map.length; i++) {
	    for (int j = 0; j < map[i].length; j++) {
		System.out.print(map[i][j]);
	    }
	    System.out.println();
	}
    }
    public static void main(String[] args) {
	outMap(getRange(0, 0, 10, 10));
    }
}
