package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import cobol.Cobol2XML;

public class EndToEndTests {

	@Test
	/*
	 * tests the two types of non contiguous data items, one with a specified format
	 * and one without. the specified format should be shown and the none specified
	 * format should be "default".
	 */
	public void testNonContiguousDataItem() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/nonContiguousDataItem.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file
		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<nonContiguousDataItem>"));
			assertTrue(r.readLine().trim().equals("<nonContiguousDataItem name=\"rest_divide\"/>"));
			assertTrue(r.readLine().trim().equals("<rest_divide Line_Number=\"77\"/>"));
			assertTrue(r.readLine().trim().equals("<rest_divide Pic_Value=\"99\"/>"));
			assertTrue(r.readLine().trim().equals("<rest_divide format=\"comp-x\"/>"));
			assertTrue(r.readLine().trim().equals("</nonContiguousDataItem>"));
			r.readLine(); // skips lines that will be the same
			r.readLine();
			r.readLine();
			r.readLine();
			assertTrue(r.readLine().trim().equals("<rest_divide format=\"Default\"/>"));
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testProgramID() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/programID.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<Program-ID>base_jb</Program-ID>")); // asserts that the program ID
																						// line is parsed correctly
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}

	}

	@Test
	public void testSectionName() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/sectionName.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<section>working-storage</section>")); // asserts that the working
																							// storage line is parsed
																							// correctly
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testDateWritten() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/dateWritten.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<day-date-written>7</day-date-written>")); // asserts that the date
																								// written line is
																								// parsed correctly
			assertTrue(r.readLine().trim().equals("<month-date-written>jul</month-date-written>"));
			assertTrue(r.readLine().trim().equals("<year-date-written>1995</year-date-written>"));
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testCommentLine() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/commentLine.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<comment>convert from decimal to base system</comment>")); // asserts
																												// that
																												// the
																												// cline
																												// is
																												// parsed
																												// correctly
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testConstantValue() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/constantValue.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<constant>")); // asserts that the opening constant tag is parsed
																	// correctly
			assertTrue(r.readLine().trim().equals("<constant name=\"base_2\"/>")); // asserts that the constant name is
																					// parsed correctly
			assertTrue(r.readLine().trim().equals("<base_2 Line_Number=\"88\"/>")); // asserts that the constant line
																					// number is parsed correctly
			assertTrue(r.readLine().trim().equals("<base_2 Value=\"2.0\"/>")); // asserts that the constant base value
																				// is parsed correctly
			assertTrue(r.readLine().trim().equals("</constant>")); // asserts that the constant closing tag is parsed
																	// correctly
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testDivisionName() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/divisionName.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<division>identification</division>")); // asserts that the Division
																							// Name line is parsed
																							// correctly
			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}

	@Test
	public void testDisplay() {
		String[] args = new String[2];
		args[0] = "endToEndTestInput/display.cbl"; // input file
		args[1] = "endToEndTestInput/output.xml"; // output file

		try {
			Cobol2XML.main(args);
			InputStream is = new FileInputStream(args[1]); // reads from output file
			BufferedReader r = new BufferedReader(new InputStreamReader(is));
			r.readLine();
			r.readLine(); // skips two lines because they will always be generated regardless
			assertTrue(r.readLine().trim().equals("<display>window erase</display>")); // asserts that the 1st Display
																						// Line outputs correctly
			assertTrue(r.readLine().trim().equals("<display>\"Base:  \" no</display>")); // asserts that the 2nd Display
																							// Line with quoted text
																							// outputs correctly
			assertTrue(
					r.readLine().trim().equals("<display>\"Base: \" current_base \" value: \" entry_char</display>")); // asserts that the 3rd Display Line with double quoted text outputs correctly

			r.close();
		} catch (Exception e) {
			fail("Cobol2XML threw an Exception");
		}
	}
}
