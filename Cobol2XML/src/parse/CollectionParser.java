/*
 * @(#)CollectionParser.java	 1.0.0
 *
 * Copyright (c) 1999 Steven J. Metsker
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

package parse;

import java.util.*;

public abstract class CollectionParser extends Parser {
	/**
	 * the parsers this parser is a collection of
	 */
	protected Vector<Parser> subparsers = new Vector<Parser>(); // holds parsers given in construction (also has one add
																// method)

	/**
	 * Supports subclass constructors with no arguments.
	 */
	public CollectionParser() {
	}

	/**
	 * Supports subclass constructors with a name argument
	 *
	 * @param string the name of this parser
	 */
	public CollectionParser(String name) {
		super(name);
	}

	/**
	 * A convenient way to construct a CollectionParser with the given parser.
	 */
	public CollectionParser(Parser p) {
		subparsers.add(p);
		;
	}

	/**
	 * A convenient way to construct a CollectionParser with the given parsers.
	 */
	public CollectionParser(Parser p1, Parser p2) {
		subparsers.add(p1);
		subparsers.add(p2);
	}

	/**
	 * A convenient way to construct a CollectionParser with the given parsers.
	 */
	public CollectionParser(Parser p1, Parser p2, Parser p3) {
		subparsers.add(p1);
		subparsers.add(p2);
		subparsers.add(p3);
	}

	/**
	 * A convenient way to construct a CollectionParser with the given parsers.
	 */
	public CollectionParser(Parser p1, Parser p2, Parser p3, Parser p4) {
		//
		subparsers.add(p1);
		subparsers.add(p2);
		subparsers.add(p3);
		subparsers.add(p4);
	}

	/**
	 * Adds a parser to the collection.
	 *
	 * @param Parser the parser to add
	 *
	 * @return this
	 */
	public CollectionParser add(Parser e) {
		subparsers.addElement(e);
		return this;
	}

	/**
	 * Return this parser's subparsers.
	 *
	 * @return Vector this parser's subparsers
	 */
	public Vector<Parser> getSubparsers() {
		return subparsers;
	}

	/**
	 * Helps to textually describe this CollectionParser.
	 *
	 * @returns the string to place between parsers in the collection
	 */
	protected abstract String toStringSeparator();

	/*
	 * Returns a textual description of this parser.
	 */
	protected String unvisitedString(Vector<Parser> visited) {
		StringBuffer buf = new StringBuffer("<");
		boolean needSeparator = false;
		Enumeration<Parser> e = subparsers.elements();
		while (e.hasMoreElements()) {
			if (needSeparator) {
				buf.append(toStringSeparator());
			}
			Parser next = e.nextElement();
			buf.append(next.toString(visited));
			needSeparator = true;
		}
		buf.append(">");
		return buf.toString();
	}
}