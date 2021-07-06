package me.gabeg.sicksends.problem.gradingsystem;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 */
public class SsGradeNode
{

	public static final String TAG = "SsGradeNode";

	/**
	 * Key for the node.
	 */
	private final String mKey;

	/**
	 * Depth of the node, with respect to the parent.
	 */
	private int mDepth;

	/**
	 * Children of the node.
	 */
	private LinkedHashMap<String, SsGradeNode> mChildMap;

	/**
	 */
	public SsGradeNode(String key)
	{
		this.mKey = key;
		this.mDepth = 0;
		this.mChildMap = new LinkedHashMap<>();
	}

	/**
	 */
	public SsGradeNode(String key, String... children)
	{
		this(key);
		this.addChildren(children);
	}

	/**
	 * Add a child to the node.
	 */
	public SsGradeNode addChild(String child)
	{
		SsGradeNode node = new SsGradeNode(child);
		int depth = this.getDepth();

		node.setDepth(depth+1);
		this.getChildMap().put(child, node);
		return node;
	}

	/**
	 * Add children to the node.
	 */
	public SsGradeNode addChildren(String... children)
	{
		for (String child : children)
		{
			this.addChild(child);
		}

		return this;
	}

	/**
	 * @return The node or subnode that matches a key.
	 */
	public SsGradeNode findChild(String key)
	{
		ArrayDeque<SsGradeNode> queue = new ArrayDeque<>();

		queue.addAll(this.getChildren());

		while (!queue.isEmpty())
		{
			SsGradeNode node = queue.remove();
			if (node.getKey().equals(key))
			{
				return node;
			}

			queue.addAll(node.getChildren());
		}

		return null;
	}

	/**
	 * @return All children and subchildren, in BFS order.
	 */
	public Collection<SsGradeNode> getAll()
	{
		List<SsGradeNode> all = new ArrayList<>();
		ArrayDeque<SsGradeNode> queue = new ArrayDeque<>();

		queue.add(this);

		while (!queue.isEmpty())
		{
			SsGradeNode node = queue.remove();
			Collection<SsGradeNode> children = node.getChildren();

			all.addAll(children);
			queue.addAll(children);
		}

		return all;
	}

	/**
	 * @return All children at a depth.
	 */
	public Collection<SsGradeNode> getAllAtDepth(int depth)
	{
		Collection<SsGradeNode> allatdepth = new ArrayList<>();
		if (depth < 0)
		{
			return allatdepth;
		}

		for (SsGradeNode node : this.getAll())
		{
			if (node.getDepth() == depth)
			{
				allatdepth.add(node);
			}
			else if (!allatdepth.isEmpty())
			{
				break;
			}
		}

		return allatdepth;
	}

	/**
	 * @return Node that matches a key.
	 */
	public SsGradeNode getChild(String key)
	{
		return this.getChildMap().get(key);
	}

	/**
	 * @return Map of the children.
	 */
	public LinkedHashMap<String, SsGradeNode> getChildMap()
	{
		return this.mChildMap;
	}

	/**
	 * @return Number of children.
	 */
	public int getChildCount()
	{
		return this.getChildMap().size();
	}

	/**
	 * @return Keys of all the children.
	 */
	public Set<String> getChildKeys()
	{
		return this.getChildMap().keySet();
	}

	/**
	 * @return The children of the node.
	 */
	public Collection<SsGradeNode> getChildren()
	{
		return this.getChildMap().values();
	}

	/**
	 * @return The depth of the node, with respect to the root node.
	 */
	public int getDepth()
	{
		return this.mDepth;
	}

	/**
	 * @return The key of the node.
	 */
	public String getKey()
	{
		return this.mKey;
	}

	/**
	 * Check if a node has a letter such as (a, b, c, and so on).
	 *
	 * @param  node  A node.
	 *
	 * @return True if a node has a letter in it, and False otherwise.
	 */
	public static boolean hasLetter(SsGradeNode node)
	{
		for (SsGradeNode child : node.getAll())
		{
			if (SsGradeNode.isLetter(child))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * @see #hasLetter(SsGradeNode)
	 */
	public boolean hasLetter()
	{
		return SsGradeNode.hasLetter(this);
	}

	/**
	 * Check if a node has a plus/minus operator.
	 */
	public static boolean hasOperator(SsGradeNode node)
	{
		for (SsGradeNode child : node.getAll())
		{
			if (SsGradeNode.isOperator(child))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * @see #hasOperator(SsGradeNode)
	 */
	public boolean hasOperator()
	{
		return SsGradeNode.hasOperator(this);
	}

	/**
	 * Check if a grade component is a letter or not.
	 *
	 * @param  item  A grade component.
	 *
	 * @return True if a grade component has a letter in it, and False otherwise.
	 */
	public static boolean isLetter(String item)
	{
		for (char c : item.toCharArray())
		{
			if (Character.isLetter(c))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if the key of a node is a letter or not.
	 *
	 * @see #isLetter(String)
	 */
	public static boolean isLetter(SsGradeNode node)
	{
		String key = node.getKey();
		return SsGradeNode.isLetter(key);
	}

	/**
	 * @see #isLetter(SsGradeNode)
	 */
	public boolean isLetter()
	{
		return SsGradeNode.isLetter(this);
	}

	/**
	 * Check if a grade component has a plus or minus operator in it.
	 *
	 * @param  item  A grade component.
	 *
	 * @return True if a grade component has an operator in it, and False otherwise.
	 */
	public static boolean isOperator(String item)
	{
		for (char c : item.toCharArray())
		{
			if ((c == '+') || (c == '-'))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Check if the key of a node is an operator or not.
	 *
	 * @see #isOperator(String)
	 */
	public static boolean isOperator(SsGradeNode node)
	{
		String key = node.getKey();
		return SsGradeNode.isOperator(key);
	}

	/**
	 * @see #isOperator(SsGradeNode)
	 */
	public boolean isOperator()
	{
		return SsGradeNode.isOperator(this);
	}

	/**
	 * Set the depth of the node, with respect to the root node.
	 *
	 * @param  depth  Depth of the node.
	 */
	public void setDepth(int depth)
	{
		this.mDepth = depth;
	}

}
