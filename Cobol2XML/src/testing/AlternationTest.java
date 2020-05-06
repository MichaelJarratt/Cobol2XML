package testing;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;

import cobol.CobolParser;
import parse.Alternation;
import parse.Parser;

public class AlternationTest {

	@Test
	// tests that Alternation created by CobolParser has the correct number of
	// sequences
	// (a Sequence is a series of Tokens that represents a line of Cobol)
	public void testNumberOfSequences() {
		CobolParser cobolParser = new CobolParser(); // is responsible for creation of Alternations
		Alternation alternation = (Alternation) CobolParser.start(); // creates new Alternation with sequences
		Vector<Parser> subparsers = alternation.getSubparsers(); // gets Sequences from Alternation
		assertTrue(subparsers.size() == 8);
	}

}
