package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;

public class CobolTest
{

	@Test
	/*
	 * this test case runs through all of Cobols getters and setters and asserts that they
	 * return the correct values and throw no errors.
	 */
	public void testSettersAndGetters()
	{
		String input = "String with stuff in it";
		String output;
		int numInput = 100;
		int numOutput;
		Cobol cobol = new Cobol();
		
		cobol.setCommentLine(input);
		output = cobol.getCommentLine();
		assertTrue(input.equals(output));
		
		cobol.setConstantName(input);
		output = cobol.getConstantName();
		assertTrue(input.equals(output));
		
		cobol.setConstantValue(numInput);
		numOutput = (int) cobol.getConstantValue();
		assertTrue(numInput == numOutput);
		
		cobol.setDayDateWritten(numInput);
		numOutput = (int) cobol.getDayDateWritten();
		assertTrue(numInput == numOutput);
		
		cobol.setDisplayLine(input);
		output = cobol.getDisplayLine();
		assertTrue(input.equals(output));
		
		cobol.setDivisionName(input);
		output = cobol.getDivisionName();
		assertTrue(input.equals(output));
		
		cobol.setFormat(input);
		output = cobol.getFormat();
		assertTrue(input.equals(output));
		
		cobol.setLineNumber(numInput);
		numOutput = (int) cobol.getLineNumber();
		assertTrue(numInput == numOutput);
		
		cobol.setMonthDateWritten(input);
		output = cobol.getMonthDateWritten();
		assertTrue(input.equals(output));
		
		cobol.setNonContiguousDataItemFormat(input);
		output = cobol.getFormat();
		assertTrue(input.equals(output));
		
		cobol.setnonContiguousDataItemName(input);
		output = cobol.getNonContiguousDataItemName();
		assertTrue(input.equals(output));
		
		cobol.setPic(numInput);
		numOutput = (int) cobol.getPic();
		assertTrue(numInput == numOutput);
		
		cobol.setProgram_ID(input);
		output = cobol.getProgram_ID();
		assertTrue(input.equals(output));
		
		cobol.setSectionName(input);
		output = cobol.getSectionName();
		assertTrue(input.equals(output));
		
		cobol.setYearDateWritten(numInput);
		numOutput = (int) cobol.getYearDateWritten();
		assertTrue(numInput == numOutput);
		}

}
