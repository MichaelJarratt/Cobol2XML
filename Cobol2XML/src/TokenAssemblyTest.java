import static org.junit.Assert.*;

import org.junit.Test;

import parse.tokens.Token;
import parse.tokens.TokenAssembly;

public class TokenAssemblyTest {

	@Test
	public void test() {
		TokenAssembly ta = new TokenAssembly("token1 token2"); // creates token assembly with string, token assembly
																// creates token string which in turn creates tokenizer
		System.out.println((Token) ta.pop());
		fail();
	}

}
