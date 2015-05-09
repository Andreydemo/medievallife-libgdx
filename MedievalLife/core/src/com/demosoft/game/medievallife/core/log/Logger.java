package com.demosoft.game.medievallife.core.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Logger extends AbstractLogger implements Serializable {

	private static final long serialVersionUID = 4876202325629010000L;

	private static List<LogRow> log = new ArrayList<>(1000);

	public List<LogRow> getAllLog() {
		return log;
	}

	public void logDebug(String message) {
		addMessage(LogRow.logDebug(message));
	}

	public void logError(String message) {
		addMessage(LogRow.logError(message));
	}

	public void logWarning(String message) {
		addMessage(LogRow.logWarning(message));
	}

	public void logInfo(String message) {
		addMessage(LogRow.logInfo(message));
	}

	public void addMessage(LogRow row) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		row.setCaller(stack[2]);
		log.add(row);
		for (AbstractLogger logger : getLoggers()) {
			logger.addMessage(row);
		}
	}

	public List<LogRow> getLogToLevel(LogLevel level) {
		List<LogRow> result = new ArrayList<>();
		for (LogRow logRow : log) {
			if (logRow.getLevel().getPermLevel() >= level.getPermLevel()) {
				result.add(logRow);
			}
		}
		return result;
	}

	public List<LogRow> getLogByLevel(LogLevel level) {
		List<LogRow> result = new ArrayList<>();
		for (LogRow logRow : log) {
			if (logRow.getLevel().getPermLevel() == level.getPermLevel()) {
				result.add(logRow);
			}
		}
		return result;
	}

	/**
	 * @Override with empty method for main logger
	 */
	@Override
	protected void init() {
		logInfo("Logger inited");
	}
}