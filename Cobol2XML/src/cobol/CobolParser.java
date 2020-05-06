/*
 * @(#)CobolParser.java	 0.1.0
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package cobol;

import parse.Alternation;
import parse.Empty;
import parse.Parser;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Num;
import parse.tokens.QuotedString;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.Word;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code
	 * constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object as an assembly's target.
	 *
	 * @return a parser that will recognize and build a <object>COBOL</object> from
	 *         a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();

		Symbol fullstop = new Symbol('.');
		fullstop.discard();

		a.add(ProgramID()); // adds Sequence to Alternation

		a.add(DivisionName());

		a.add(SectionName());

		a.add(DateWritten());

		// our changes

		a.add(DisplayLine());

		a.add(DisplayLineQuotedText());

		a.add(DisplayLineDoubleQuotedText());

		a.add(constantValue());

		a.add(commentLine());

		a.add(nonContiguousDataItem());

		a.add(nonContiguousDataItemWithFormat());

		// end of our changes

		a.add(new Empty());
		return a;
	}

	// start of our changes
	private Parser DisplayLine() {
		Sequence s = new Sequence(); // display line composed of
		s.add(new CaselessLiteral("display").discard()); // a word which can be ignored
		s.add(new Word()); // a word
		s.add(new Word().setAssembler(new DisplayLineAssembler())); // the rest of the line's string
		return s;
	}

	private Parser DisplayLineQuotedText() {
		Sequence s = new Sequence(); // display line composed of
		s.add(new CaselessLiteral("display").discard()); // a word which can be ignored
		s.add(new QuotedString()); // a string enclosed by quotes example: "String"
		s.add(new Word().setAssembler(new DisplayLineAssembler())); // the rest of the line's string
		return s;
	}

	private Parser DisplayLineDoubleQuotedText() {
		Sequence s = new Sequence(); // display line composed of
		s.add(new CaselessLiteral("display").discard()); // a word which can be ignored
		s.add(new QuotedString()); // the 1st string enclosed by quotes example: "String1"
		s.add(new Word()); // a word linked with the 1st QuotedString
		s.add(new QuotedString()); // the 2nd string enclosed by quotes example: "String2"
		s.add(new Word().setAssembler(new DisplayLineQuotedAssembler())); // a word linked with the 2nd QuotedString
		return s;
	}

	/*
	 * returns a Sequence that will recognise:
	 * 
	 * 77 [name] pic [num]
	 */
	private Parser nonContiguousDataItem() {
		Sequence s = new Sequence(); // sequence of tokens this line is made of
		s.add(new Num()); // line has to start with 77
		s.add(new Word()); // name of the data item
		s.add(new CaselessLiteral("pic").discard()); // "pic" needed to identify line but not to write to XML
		s.add(new Num());
		s.setAssembler(new NonContiguousDataItemAssembler());
		return s;
	}

	/*
	 * returns a Sequence that will recognise:
	 * 
	 * 77 [name] pic [num] [format(word)]
	 */
	private Parser nonContiguousDataItemWithFormat() {
		Sequence s = new Sequence(); // sequence of tokens this line is made of
		s.add(new Num()); // line has to start with 77
		s.add(new Word()); // name of the data item
		s.add(new CaselessLiteral("pic").discard()); // "pic" needed to identify line but not to write to XML
		s.add(new Num());
		s.add(new Word());
		s.add(new Symbol('.').discard());
		s.setAssembler(new NonContiguousDataItemAssembler());
		return s;
	}

	private Parser constantValue() {
		Sequence s = new Sequence(); // constant value line composed of
		s.add(new Num()); // a number
		s.add(new Word()); // a word
		s.add(new CaselessLiteral("value")); // a word which can be ignored
		s.add(new Num()); // a number
		s.setAssembler(new ConstantValueAssembler());
		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 * ***___ [comment]
	 */
	private Parser commentLine() {
		Sequence s = new Sequence(); // sequence that makes up a line of cobol
		s.add(new Symbol("*")); // first character in sequence
		s.add(new Symbol("*")); // second
		s.add(new Symbol("*")); // third ...
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Symbol("-"));
		s.add(new Word().setAssembler(new CommentLineAssembler()));
		return s;
	}

	// end of our changes

	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 * Program Identifier = Word;
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id"));
		s.add(new Symbol('.').discard());
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 * <divisionName> division;
	 *
	 */
	protected Parser DivisionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new DivisionAssembler()));
		s.add(new CaselessLiteral("division"));
		s.add(new Symbol('.').discard());
		return s;
	}

	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 * Program Identifier = Word;
	 *
	 */
	protected Parser SectionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new SectionNameAssembler()));
		s.add(new CaselessLiteral("section"));
		s.add(new Symbol('.').discard());

		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 * working-storage section;
	 *
	 */
	protected Parser DateWritten() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("date-written"));
		s.add(new Symbol('.').discard());
		s.add(new Num());
		s.add(new Symbol('-').discard());

		// This next Word actually contains month and year (which are extracted in
		// DateAssembler
		s.add(new Word());
		s.add(new Symbol('-').discard());
		s.add(new Word().discard());
		s.add(new Symbol('.').discard());
		s.setAssembler(new DateAssembler());
		return s;
	}

	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return (new CobolParser()).cobol();
	}

	/**
	 * Returns a tokenizer that does not allow spaces to appear inside the "words"
	 * that identify cobol's grammar.
	 *
	 * @return a tokenizer that does not allow spaces to appear inside the "words"
	 *         that identify cobol grammar.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', false);
		return t;
	}

}
