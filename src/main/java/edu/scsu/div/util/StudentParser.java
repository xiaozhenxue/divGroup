package edu.scsu.div.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import edu.scsu.div.model.Student;

@Component
public class StudentParser {
	
	public List<Student> parse(String text){
		String[] lines = text.split("\n");
		List<Student> ret = new ArrayList<>();
		for(String line: lines){
			List<Double> vector = Arrays.stream(line.split(";"))
					.map(e -> Double.parseDouble(e))
					.collect(Collectors.toList());					
			Student s = new Student().setVector(vector);
			ret.add(s);
		}
		return ret;
	}

}
