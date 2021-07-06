package me.gabeg.sicksends.boulder;

import me.gabeg.sicksends.problem.gradingsystem.SsGradingSystem;

/**
 * Boulder grading systems.
 */
public class SsBoulderGradingSystem
{

	/**
	 * Fontainebleau boulder grading system.
	 */
	public static class Font
		extends SsGradingSystem
	{

		/**
		 */
		public Font()
		{
			super("Fontainebleau");

			this.addChild("3").addChildren("");
			this.addChild("4").addChildren("-", "", "+");
			this.addChild("5").addChildren("-", "", "+");
			this.addChild("6").addChildren("A", "B", "C");
			this.getChild("6").getChild("A").addChildren("", "+");
			this.getChild("6").getChild("B").addChildren("", "+");
			this.getChild("6").getChild("C").addChildren("", "+");
			this.addChild("7").addChildren("A", "B", "C");
			this.getChild("7").getChild("A").addChildren("", "+");
			this.getChild("7").getChild("B").addChildren("", "+");
			this.getChild("7").getChild("C").addChildren("", "+");
			this.addChild("8").addChildren("A", "B", "C");
			this.getChild("8").getChild("A").addChildren("", "+");
			this.getChild("8").getChild("B").addChildren("", "+");
			this.getChild("8").getChild("C").addChildren("", "+");
			this.addChild("9").addChildren("A");
		}

	}

	/**
	 * UK boulder grading system.
	 */
	public static class Uk
		extends SsGradingSystem
	{

		/**
		 */
		public Uk()
		{
			super("UK");

			this.addChild("4").addChildren("a", "b", "c");
			this.addChild("5").addChildren("a", "b", "c");
			this.addChild("6").addChildren("a", "b", "c");
			this.addChild("7").addChildren("a", "b", "c");
		}

	}

	/**
	 * V-scale boulder grading system.
	 */
	public static class Vscale
		extends SsGradingSystem
	{

		/**
		 */
		public Vscale()
		{
			super("V scale");

			this.addChild("VB");
			this.addChild("V0").addChildren("-", "", "+");
			this.addChild("V1").addChildren("-", "", "+");
			this.addChild("V2").addChildren("-", "", "+");
			this.addChild("V3").addChildren("-", "", "+");
			this.addChild("V4").addChildren("-", "", "+");
			this.addChild("V5").addChildren("-", "", "+");
			this.addChild("V6").addChildren("-", "", "+");
			this.addChild("V7").addChildren("-", "", "+");
			this.addChild("V8").addChildren("-", "", "+");
			this.addChild("V9").addChildren("-", "", "+");
			this.addChild("V10").addChildren("-", "", "+");
			this.addChild("V11").addChildren("-", "", "+");
			this.addChild("V12").addChildren("-", "", "+");
			this.addChild("V13").addChildren("-", "", "+");
			this.addChild("V14").addChildren("-", "", "+");
			this.addChild("V15").addChildren("-", "", "+");
			this.addChild("V16").addChildren("-", "", "+");
			this.addChild("V17").addChildren("-", "", "+");
		}

	}

	/**

	<string-array name="grades_rope_bra">
		<item>I sup</item>
		<item>II</item>
		<item>II sup</item>
		<item>III</item>
		<item>III sup</item>
		<item>IV</item>
		<item>IV sup</item>
		<item>V</item>
		<item>V sup</item>
		<item>VI</item>
		<item>VI sup</item>
		<item>VIIa</item>
		<item>VIIb</item>
		<item>VIIc</item>
		<item>VIIIa</item>
		<item>VIIIb</item>
		<item>VIIIc</item>
		<item>IXa</item>
		<item>IXb</item>
		<item>IXc</item>
		<item>Xa</item>
		<item>Xb</item>
		<item>Xc</item>
		<item>XIa</item>
		<item>XIb</item>
		<item>XIc</item>
		<item>XIIa</item>
		<item>XIIb</item>
		<item>XIIc</item>
	</string-array>

	<string-array name="grades_rope_pol">
		<item>I-</item>
		<item>I</item>
		<item>I+</item>
		<item>II-</item>
		<item>II</item>
		<item>II+</item>
		<item>III-</item>
		<item>III</item>
		<item>III+</item>
		<item>IV-</item>
		<item>IV</item>
		<item>IV+</item>
		<item>V-</item>
		<item>V</item>
		<item>V+</item>
		<item>VI-</item>
		<item>VI</item>
		<item>VI+</item>
		<item>VI.1</item>
		<item>VI.1+</item>
		<item>VI.2</item>
		<item>VI.2+</item>
		<item>VI.3</item>
		<item>VI.3+</item>
		<item>VI.4</item>
		<item>VI.4+</item>
		<item>VI.5</item>
		<item>VI.5+</item>
		<item>VI.6</item>
		<item>VI.6+</item>
		<item>VI.7</item>
		<item>VI.7+</item>
		<item>VI.8</item>
		<item>VI.8+</item>
		<item>VI.9</item>
		<item>VI.9+</item>
	</string-array>
	*/

}
