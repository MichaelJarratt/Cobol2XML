package testing;
import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class DivisionTest {

	@Test
	/*
	 * tests that a Division can be parsed and that the correct values are set in the Cobol instance
	 */
	public void testDivisionID() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();

		t.setString("identification Division.");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);

		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();

		assertEquals(c.getDivisionName(), "identification");
	}
}
