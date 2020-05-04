package testing;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.CommentLineAssembler;
import parse.Terminal;
import parse.tokens.Token;
import parse.tokens.TokenAssembly;

public class CommentLineAssemblerTest
{

	@Test
	public void testWorkOn()
	{
		TokenAssembly tokas = new TokenAssembly("***--- comment line here");
		CommentLineAssembler assembler = new CommentLineAssembler();
		Terminal terminal = new Terminal();
		assembler.workOn(tokas);
	}

}
