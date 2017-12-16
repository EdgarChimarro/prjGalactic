package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import prjGalaxy.InputOutput;
import util.Constant;

public class InputOutputTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Constant.PRIMARY_VALUES.put("glob","I");
		Constant.PRIMARY_VALUES.put("prok","V");
		Constant.PRIMARY_VALUES.put("pish","X");
		Constant.PRIMARY_VALUES.put("tegj","L");		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testProcessAllFile() {
		InputOutput processFile = new InputOutput();
		processFile.readFile();
	}
	
//	@Test
	public void testHowQuestions() {
		InputOutput reads = new InputOutput();				
		reads.processHowMuchQuestion("how much is glob prok ?", Constant.QUESTION_HOW);
	}

//	@Test
	public void testReadCredits() {
		InputOutput reads = new InputOutput();
		reads.validateCredits("glob glob Silver is 34 credits", " is ", Constant.DELIMITER_PRIMARY_CREDITS);
	}
}
