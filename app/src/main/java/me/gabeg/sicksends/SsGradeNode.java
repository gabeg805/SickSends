package me.gabeg.sicksends;

import android.util.Log;
import java.util.ArrayDeque;
import java.util.Arrays;
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
	 * Set the depth of the node, with respect to the root node.
	 *
	 * @param  depth  Depth of the node.
	 */
	public void setDepth(int depth)
	{
		this.mDepth = depth;
	}

}
