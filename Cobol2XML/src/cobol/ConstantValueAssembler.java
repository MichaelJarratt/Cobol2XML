package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.*;

public class ConstantValueAssembler extends Assembler {

	// what to do when parser finds a match
	@Override
	// test case: 88 base_2 value 2 ---- where value = token 3
	public void workOn(Assembly a) // assembly is a collection of tokens?
	{
		Cobol c = new Cobol(); // represents a line of code?
		Token t = (Token) a.pop(); // token represents a single word of code? "2"?
		c.setConstantValue(t.nval());
		// System.out.println("token string[3] = "+ c.getConstantValue());

		t = (Token) a.pop(); // pops next token "value"?
		// System.out.println("token string[2] = "+t.sval());
		// "value" is an assignment and is not needed by parser

		t = (Token) a.pop(); // pops next "base_2"? which is name of constant
		c.setConstantName(t.sval());
		// System.out.println("token string[1] = "+c.getConstantName());

		t = (Token) a.pop(); // pops last token "88"? line number
		c.setLineNumber((int) Math.round(t.nval()));
		// System.out.println("token string[0] = "+c.getLineNumber());

		a.setTarget(c);
	}

}
