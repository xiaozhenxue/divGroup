package edu.scsu.div.util;

import java.io.ByteArrayOutputStream;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.Executor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Component;

@Component
public class MdgpRunner {

	// private static final String COMMAND = "java -jar %s SO %s %d 1 >%s";
	private static final String COMMAND = "java -jar %s SO %s %d 1";

	/*
	 * public boolean runScript(String packagePath, String inputPath, int
	 * duration, String outputPath){ try { String line = String.format(COMMAND,
	 * packagePath, inputPath, duration, outputPath); System.out.println(line);
	 * CommandLine cmdLine = CommandLine.parse(line); DefaultExecutor executor =
	 * new DefaultExecutor(); executor.execute(cmdLine); return true; } catch
	 * (Exception e) { return false; } }
	 */

	public String runScript(String packagePath, String inputPath, int duration, String outputPath) {
		try {
			String line = String.format(COMMAND, packagePath, inputPath, duration, outputPath);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			CommandLine commandline = CommandLine.parse(line);
			DefaultExecutor exec = new DefaultExecutor();
			PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
			exec.setStreamHandler(streamHandler);
			exec.execute(commandline);
			return (outputStream.toString());
		} catch (Exception e) {
			return "";
		}
	}

}
