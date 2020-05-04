package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

import cobol.Cobol2XML;

public class EndToEndTests
{

	@Test
	public void testProgramID() throws Exception
	{
		String[] args = new String[2];
		args[0] = "endToEndTestInput/base.cbl"; //input file
		args[1] = "endToEndTestInput/output.xml"; //output file
		//Cobol2XML cobol2Xml = new Cobol2XML(args);
		Cobol2XML.main(args);
		
		InputStream is = new FileInputStream(args[1]); //reads from output file
		BufferedReader r = 	new BufferedReader(new InputStreamReader(is));
		r.readLine();
		r.readLine(); //skips two lines because they will always be generated regardless
		assertTrue(r.readLine().equals("<Program-ID>base_jb</Program-ID>")); //asserts that the program ID line is parsed correctly
	}

}
