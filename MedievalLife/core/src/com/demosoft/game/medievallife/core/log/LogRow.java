package com.demosoft.game.medievallife.core.log;

import java.io.Serializable;
import java.util.Calendar;

public class LogRow implements Serializable {

	private static final long serialVersionUID = 8131538690654431090L;

	private StackTraceElement caller;

	private String message;

	private LogLevel level;

	private Calendar date;
	
	public LogRow(String message, LogLevel level) {
		this.message = message;
		this.level = level;
		this.date = Calendar.getInstance();
	}

	public static LogRow logDebug(String message) {
		return new LogRow(message, LogLevel.DEBUG);
	}

	public static LogRow logError(String message) {
		return new LogRow(message, LogLevel.ERROR);
	}

	public static LogRow logWarning(String message) {
		return new LogRow(message, LogLevel.WARNING);
	}

	public static LogRow logInfo(String message) {
		return new LogRow(message, LogLevel.INFO);
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LogLevel getLevel() {
		return level;
	}

	public void setLevel(LogLevel level) {
		this.level = level;
	}

	public StackTraceElement getCaller() {
		return caller;
	}

	public void setCaller(StackTraceElement caller) {
		this.caller = caller;
	}
}
