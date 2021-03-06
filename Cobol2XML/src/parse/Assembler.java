package parse;

import java.util.*;

public abstract class Assembler {
	/**
	 * Returns a vector of the elements on an assembly's stack that appear before a
	 * specified fence.
	 * <p>
	 * Sometimes a parser will recognize a list from within a pair of parentheses or
	 * brackets. The parser can mark the beginning of the list with a fence, and
	 * then retrieve all the items that come after the fence with this method.
	 *
	 * @param assembly a assembly whose stack should contain some number of items
	 *                 above a fence marker
	 * 
	 * @param object   the fence, a marker of where to stop popping the stack
	 * 
	 * @return Vector the elements above the specified fence
	 * 
	 */
	public static Vector<Object> elementsAbove(Assembly a, Object fence) {
		Vector<Object> items = new Vector<Object>();

		while (!a.stackIsEmpty()) {
			Object top = a.pop();
			if (top.equals(fence)) {
				break;
			}
			items.addElement(top);
		}
		return items;
	}

	/**
	 * This is the one method all subclasses must implement. It specifies what to do
	 * when a parser successfully matches against a assembly.
	 * 
	 * takes assembly as input and creates a Cobol object which is given to the
	 * assembly
	 *
	 * @param Assembly the assembly to work on
	 */
	public abstract void workOn(Assembly a);
}
