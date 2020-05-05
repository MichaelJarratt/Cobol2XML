package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class NonContiguousDataItemAssembler extends Assembler
{

	@Override
	public void workOn(Assembly a)
	{
		Cobol c = new Cobol();
		
		Token t = (Token) a.pop(); //first value is the value of "pic"
		
		c.setPic((int)t.nval());
		
		a.pop(); //skips string "pic"
		
		t = (Token)a.pop(); //third value is the name
		c.setnonContiguousDataItemName(t.sval());
		
		t = (Token)a.pop(); //fourth value is the line number
		c.setLineNumber((int)t.nval()); //casts double to int
		
	}

}
