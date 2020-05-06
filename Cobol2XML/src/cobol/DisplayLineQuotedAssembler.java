package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

// Assembler class that will create an object to assemble the XML output line for
// Cobol inputs that match "DISPLAY + QuotedText + Word + QuotedText + Word"
public class DisplayLineQuotedAssembler extends Assembler {

	public DisplayLineQuotedAssembler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol(); // represents line of Cobol
		Token t = (Token) a.pop(); // represents 2nd 'word' in the line

		c.setDisplayLine(t.sval());
		// use the new prepend method to pop all of the values in the correct order
		// (FILO is the wrong order so prepend is needed to reverse it to get right
		// order)
		c.prependToDisplayLine(((Token) a.pop()).sval()); // prepend 2nd quoted text
		c.prependToDisplayLine(((Token) a.pop()).sval()); // prepend 1st word
		c.prependToDisplayLine(((Token) a.pop()).sval()); // prepend 1st quoted text
		a.setTarget(c);
	}

}
