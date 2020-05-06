package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class NonContiguousDataItemAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();

		// if the first token is a word it is the format
		// (nonContiguousDataItemWithFormat)
		// otherwise it is the "pic" value (nonContiguousDataItem)
		Token t = (Token) a.peekStack();
		if (t.ttype() == Token.TT_WORD) {
			t = (Token) a.pop();
			c.setFormat(t.sval()); // sets the format to the word from the line
		} else {
			c.setFormat("Default"); // sets the defualt format of "default"
		}

		t = (Token) a.pop(); // first value is the value of "pic"

		c.setPic((int) t.nval());

		// a.pop(); //skips string "pic"

		t = (Token) a.pop(); // third value is the name
		c.setnonContiguousDataItemName(t.sval());

		t = (Token) a.pop(); // fourth value is the line number
		c.setLineNumber((int) t.nval()); // casts double to int

		a.setTarget(c); // sets the Cobol Object represting the line to be converted to XML as this
						// assemblers target
	}

}
