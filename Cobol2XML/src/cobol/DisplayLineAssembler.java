package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class DisplayLineAssembler extends Assembler
{

	public DisplayLineAssembler()
	{
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void workOn(Assembly a)
	{
		Cobol c = new Cobol(); // represents line of code
		Token t = (Token) a.pop(); //represents a single word in the line
		
		c.setDisplayLine(t.sval());
		c.prependToDisplayLine(((Token)a.pop()).sval());
		a.setTarget(c);
	}

}
