package edu.scsu.div.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.scsu.div.model.GroupRequest;
import edu.scsu.div.model.Student;
import edu.scsu.div.util.DistanceCalculator;
import edu.scsu.div.util.FileUtil;
import edu.scsu.div.util.GroupParser;
import edu.scsu.div.util.MdgpFormatter;
import edu.scsu.div.util.MdgpRunner;
import edu.scsu.div.util.StudentParser;

@Service
public class GroupService {
	
	private static final String TEMP_PATH = "/Users/xxue/Documents/proj/divGroup/lib/";
	private static final int DURATION = 3000;
	
	
	@Autowired
	private DistanceCalculator distanceCalculator;
	
	@Autowired
	private StudentParser studentParser;
	
	@Autowired
	private MdgpFormatter mdgpFormatter;
	
	@Autowired
	private MdgpRunner mdgpRunner;
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private GroupParser groupParser;

	public List<List<Integer>> group(GroupRequest request) {
		List<List<Integer>> ret = new ArrayList<>();
		int numberStudent = request.getNumberStudent();
		int numberGroups = request.getNumberGroups();
		List<Student> students = studentParser.parse(request.getInput());
		double[][] distances = createDistanceMatrix(students);
		String inputContent = mdgpFormatter.createFileContent(distances, numberStudent, numberGroups);
		boolean writeSuccess = fileUtil.writeFile(inputContent, TEMP_PATH + "input.txt");
		String outputContent = mdgpRunner.runScript(TEMP_PATH + "mdgp_jors_2011.jar", 
				TEMP_PATH + "input.txt", DURATION,  TEMP_PATH + "output.txt");
		ret = groupParser.parse(outputContent);
		return ret;
	}

	private double[][] createDistanceMatrix(List<Student> students) {
		int size = students.size();
		double[][] ret = new double[size][size];
		for(int i=0; i<size; i++)
			for(int j=i+1; j<size; j++){
				ret[i][j] = distanceCalculator.consine(students.get(i).getVector(), students.get(j).getVector());
			}
		return ret;
	}
}
