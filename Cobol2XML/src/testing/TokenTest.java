package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import parse.tokens.Token;
import parse.tokens.TokenType;

public class TokenTest {

	@Test
	// testing to find out how to identify a type of token
	public void testTTypeComparison() {
		Token token = new Token("aWord"); // creates token of type "word"
		assertTrue(token.ttype() == Token.TT_WORD);
	}

}
