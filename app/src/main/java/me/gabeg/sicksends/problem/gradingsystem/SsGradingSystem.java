package me.gabeg.sicksends.problem.gradingsystem;

import java.util.ArrayList;
import java.util.List;

/**
 * Access different climbing grades for different grade names.
 *
 * TODO: Am I really using SsGradingSystem?
 */
public class SsGradingSystem
	extends SsGradeNode
{

	private static final String TAG = "SsGradingSystem";

	/**
	 */
	public SsGradingSystem(String key)
	{
		super(key);
	}

	/**
	 * Possible cases:
	 *
	 * Has a letter and operator and normal
	 * 		-> Operator and normal
	 * 				-> Null
	 * Has a letter and operator
	 * 		-> Operator
	 * 				-> Null
	 *
	 * Has letter and normal
	 * 		-> Null
	 * Has operator and normal
	 * 		-> Null
	 *
	 * Has a letter
	 * 		-> Null
	 * Has operator
	 * 		-> Null
	 * Has normal
	 * 		-> Null
	 *
	 */

	/**
	 * Check if a grade has a normal grade, which is to say, no sub-grades.
	 */
	public boolean hasNormal(String grade)
	{
		SsGradeNode node = this.getChild(grade);

		for (SsGradeNode child : node.getAll())
		{
			if (this.isNormal(child))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * @see #isNormal(String)
	 */
	public boolean isNormal(SsGradeNode node)
	{
		String key = node.getKey();
		return this.isNormal(key);
	}

	/**
	 * Check if a grade item is normal, which is to say, with neither a letter or
	 * operator in it.
	 *
	 * @param  item  A grade or grade item.
	 *
	 * @return True if a grade item is normal, and False otherwise.
	 */
	public boolean isNormal(String item)
	{
		return item.isEmpty();
	}

	/**
	 * Find the depth where the nodes with letters as keys are.
	 *
	 * @param  grade  A grade.
	 *
	 * @return The depth of letter nodes.
	 */
	public int findLetterDepth(String grade)
	{
		SsGradeNode node = this.getChild(grade);

		for (SsGradeNode child : node.getAll())
		{
			if (SsGradeNode.isLetter(child))
			{
				return child.getDepth();
			}
		}

		return -1;
	}

	/**
	 * Find the depth where the nodes with operators as keys are.
	 *
	 * @param  grade  A grade.
	 *
	 * @return The depth of operator nodes.
	 */
	public int findOperatorDepth(String grade)
	{
		SsGradeNode node = this.getChild(grade);

		for (SsGradeNode child : node.getAll())
		{
			if (this.isOperator(child))
			{
				return child.getDepth();
			}
		}

		return -1;
	}

	/**
	 * @return All grade or grade items at a depth.
	 */
	public List<String> getAllAtDepth(int depth, String grade)
	{
		SsGradeNode node = this.getChild(grade);
		List<String> items = new ArrayList<>();

		for (SsGradeNode child : node.getAllAtDepth(depth))
		{
			items.add(child.getKey());
		}

		return items;
	}

	/**
	 * Get the list of letters used by subgrades.
	 *
	 * This is to say, if a subgrade has a letter, as well as a letter AND an
	 * operator, only one inclusion will be made to the returned list.
	 *
	 * @param  grade  A grade.
	 *
	 * @return The list of letters used by subgrades.
	 */
	public List<String> getLetters(String grade)
	{
		int depth = this.findLetterDepth(grade);
		return this.getAllAtDepth(depth, grade);
	}

	/**
	 * Will do the same as #getLetters(String), but will also add the Normal grade
	 * to the list, if the grade has that as a possibility.
	 *
	 * @param  grade  Main climbing grade.
	 *
	 * @return The list of unique letters used by subgrades.
	 */
	public List<String> getNormalLetters(String grade)
	{
		List<String> letters = this.getLetters(grade);

		if (this.hasNormal(grade))
		{
			letters.add("None");
		}

		return letters;
	}

	/**
	 * Will do the same as #getOperators(String), but will also add the Normal
	 * grade to the list, if the grade has that as a possibility.
	 *
	 * @param  grade  Main climbing grade.
	 *
	 * @return The list of subgrades that have a letter.
	 */
	public List<String> getNormalOperators(String grade)
	{
		List<String> operators = this.getOperators(grade);

		if (this.hasNormal(grade))
		{
			operators.add("None");
		}

		return operators;
	}

	/**
	 * Get the list of operators used by subgrades.
	 *
	 * This is to say, if a subgrade has a letter AND and operator, it will not be
	 * included in this list.
	 *
	 * @param  grade  A grade.
	 *
	 * @return The list of operators used by subgrades.
	 */
	public List<String> getOperators(String grade)
	{
		int depth = this.findOperatorDepth(grade);
		return this.getAllAtDepth(depth, grade);
	}

	/**
	 * Get the list of operators used by subgrades.
	 *
	 * This is to say, if a subgrade has a letter AND and operator, it will not be
	 * included in this list.
	 *
	 * @param  grade  A grade.
	 *
	 * @return The list of operators used by subgrades.
	 */
	public List<String> getOperators(String grade, String letter)
	{
		if (letter == null)
		{
			return this.getOperators(grade);
		}

		SsGradeNode node = this.getChild(grade);
		SsGradeNode subnode = node.getChild(letter);
		List<String> items = new ArrayList<>();

		for (SsGradeNode child : subnode.getChildren())
		{
			items.add(child.getKey());
		}

		return items;
	}

}
