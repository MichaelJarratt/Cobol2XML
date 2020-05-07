package testing;
import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class NonContiguousDataItemTest {

	@Test
	/*
	 * tests that a nonCOntiguous data item line can be parsed and that the correct values are set in the Cobol instance
	 */
	public void testDivisionID() {
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();

		t.setString("77  rest_divide                         pic 99 comp-x.");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);

		Cobol c = new Cobol();
		c = (Cobol) out.getTarget();

		assertEquals(c.getFormat(), "comp-x");
	}
}
