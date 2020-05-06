import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class ConstantValueTest {

	@Test
	public void testConstantValue() {
		Tokenizer t = CobolParser.tokenizer(); // empty when created
		Parser p = CobolParser.start(); // collection of parsers (ConstantValue is one such parser)

		t.setString("88  base_2  value  2"); // sets string in tokenizer to be tokenized by giving it a reader with
												// input string
		Assembly in = new TokenAssembly(t); // returns assembly with vector (mutable array) of tokens
		Assembly out = p.bestMatch(in); // constructs Cobol object

		Cobol c;
		c = (Cobol) out.getTarget(); // gets Cobol object with info of line from out

		assertEquals(c.getConstantName(), "base_2");
	}

}
