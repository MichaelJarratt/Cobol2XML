package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import parse.tokens.Token;
import parse.tokens.TokenAssembly;

public class TokenizerTest
{

	@Test
	public void lineTokenizationTest()
	{
		TokenAssembly tokenAssembly = new TokenAssembly("program-id.  base_jb.");
		assertTrue(((Token) tokenAssembly.nextElement()).sval().equals("program-id"));
	}

}
