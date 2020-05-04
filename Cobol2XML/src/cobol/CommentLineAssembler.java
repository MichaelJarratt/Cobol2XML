package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class CommentLineAssembler extends Assembler
{

	public CommentLineAssembler()
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a)
	{
		Cobol c = new Cobol(); // represents line of code
		Token t = (Token) a.pop(); //represents a single word in the line
		
		
		if(t.sval() != null) //if token has a string value
		{
			c.setCommentLine((t.sval()+" "+a.remainder(" ")).trim());
			a.setTarget(c);
		}
		

	}

}
