package com.demosoft.game.medievallife.core.log;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public abstract class AbstractLogger {

	public abstract void addMessage(LogRow row);

	private static LogLevel currentLevel = LogLevel.INFO;

	private static List<AbstractLogger> loggers = new ArrayList<>();

	@PostConstruct
	protected void init() {
		loggers.add(this);
	}

	public static List<AbstractLogger> getLoggers() {
		return loggers;
	}

	public LogLevel getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(LogLevel currentLevel) {
		setCurrentLevelS(currentLevel);
	}

	public static void setCurrentLevelS(LogLevel currentLevel) {
		AbstractLogger.currentLevel = currentLevel;
	}

	public boolean checkLevel(LogRow row) {
		return row.getLevel().getPermLevel() >= currentLevel.getPermLevel();
	}

}
