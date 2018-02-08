package edu.scsu.div.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class GroupParser {
		
	public List<List<Integer>> parse(String text){
		Map<Integer, List<Integer>> map = new HashMap<>();
		String[] lines = text.split("\n");
		String line = lines[lines.length-1];
		System.out.println("last line: " + line);
		String list = line.split(":")[1].trim();
		String string = list.substring(1, list.length()-1);
		System.out.println("last list: " + string);
		List<Integer> groups = Arrays.stream(string.split(",")).map(e -> Integer.parseInt(e.trim())).collect(Collectors.toList());
		for(int i=0; i<groups.size(); i++){
			if (!map.containsKey(groups.get(i)))
				map.put(groups.get(i), new ArrayList<Integer>());
			map.get(groups.get(i)).add(i);
		}		
		return map.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
	}

}
