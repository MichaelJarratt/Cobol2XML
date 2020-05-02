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
			c.setCommentLine(t.sval()+" "+a.remainder(" "));
			a.setTarget(c);
		}
//		StringBuffer buff = new StringBuffer();
//		while (((Token) a.peek()).sval() != null) // while there are remaining tokens with comment words in them
//		{
//			buff.append(((Token) a.pop()).sval()+" "); //appends token and a space to String Buffer
//		}
//		String commentLine = buff.toString(); //converts buffer to string
//		c.setCommentLine(commentLine.trim()); //removes extra space and sets it as the comment line for the Cobol Object
//		a.setTarget(c);
	}

}
