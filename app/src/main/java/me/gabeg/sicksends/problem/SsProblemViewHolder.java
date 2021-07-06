package me.gabeg.sicksends.problem;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import me.gabeg.sicksends.R;

/**
 * Problem view holder.
 */
public class SsProblemViewHolder
	extends RecyclerView.ViewHolder
{

	/**
	 * Grade of a problem.
	 */
	private TextView mGrade;

	/**
	 * Name of a problem.
	 */
	private TextView mName;

	/**
	 * Style of a problem.
	 */
	private TextView mStyle;

	/**
	 */
	public SsProblemViewHolder(@NonNull View itemView)
	{
		super(itemView);

		this.mGrade = itemView.findViewById(R.id.problem_grade);
		this.mName = itemView.findViewById(R.id.problem_name);
		this.mStyle = itemView.findViewById(R.id.problem_style);
	}

}
