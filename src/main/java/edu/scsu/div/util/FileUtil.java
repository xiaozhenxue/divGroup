package edu.scsu.div.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

@Component
public class FileUtil {

	public boolean writeFile(String content, String path) {
		try {
			File file = new File(path);
			FileUtils.writeStringToFile(file, content);
			return true;
		} catch (IOException e) {
			System.out.println("not able to write file");
			return false;
		}
	}

	public String readFile(String path) {
		try {
			File file = new File(path);
			return FileUtils.readFileToString(file);			
		} catch (IOException e) {
			System.out.println("not able to read file");
			return "";
		}
	}

}
