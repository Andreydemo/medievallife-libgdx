package com.demosoft.game.medievallife.core.log;

import org.springframework.stereotype.Component;

@Component
public class ConsoleLogger extends AbstractLogger {

	@Override
	public void addMessage(LogRow row) {
		if (checkLevel(row)) {
			System.out.println(row.getDate().getTime() + " " + row.getLevel() + "  - [" + row.getCaller() + "] " + row.getMessage());
		}
	}

}
