package edu.scsu.div.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;


@Component
public class MdgpFormatter {
	
	public String createFileContent(double[][] distances, int numberStudent, int numberGroups) {
		String head = createHead(numberStudent, numberGroups);
		String boundry = createBoundry(numberStudent, numberGroups);
		String body = createBody(distances);
		return head + boundry + "\n" + body;
	}
	
	private String createHead(int numberStudent, int numberGroups){
		return new StringBuilder()
				.append(numberStudent + " ")
				.append(numberGroups + " ")
				.append(numberStudent % numberGroups == 0 ? "ss " : "ds ")
				.toString();
	}
	
	private String createBoundry(int numberStudent, int numberGroups){
		int size = numberStudent / numberGroups;
		int biggerGroup = numberStudent % numberGroups;
		List<String> boundry = new ArrayList<>();
		for(int i = 0; i< numberGroups; i++){
			boundry.add(size + " " + (i < biggerGroup ? size+1 : size));
		}
		return boundry.stream().collect(Collectors.joining(" "));
	}
	
	private String createBody(double[][] distances){
		List<String> rows = new ArrayList<>();
		for (int i = 0; i < distances.length; i++) {
			for (int j = i + 1; j < distances.length; j++)
				rows.add(i + " " + j + " " + distances[i][j]);
		}
		return rows.stream().collect(Collectors.joining("\n"));
	}

}
