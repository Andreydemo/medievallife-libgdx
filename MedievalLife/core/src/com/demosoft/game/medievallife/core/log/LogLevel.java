package com.demosoft.game.medievallife.core.log;

public enum LogLevel {
	TRACE(0), DEBUG(1), INFO(2), WARNING(3), ERROR(4), FATAL(5);

	private int permLevel;

	private LogLevel(int permLevel) {
		this.permLevel = permLevel;
	}

	public int getPermLevel() {
		return permLevel;
	}

}
