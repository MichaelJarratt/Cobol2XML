package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

//Assembler class that will create an object to assemble the XML output line for
//Cobol inputs that match "DISPLAY + Word + Word" OR "DISPLAY + QuotedText + Word"
public class DisplayLineAssembler extends Assembler {

	public DisplayLineAssembler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol(); // represents line of code
		Token t = (Token) a.pop(); // represents a single word in the line

		c.setDisplayLine(t.sval());

		c.prependToDisplayLine(((Token) a.pop()).sval()); // prepend the first word
		a.setTarget(c);
	}

}
