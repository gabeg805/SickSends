package me.gabeg.sicksends.rope;

import me.gabeg.sicksends.problem.gradingsystem.SsGradingSystem;

/**
 * Rope grading systems.
 */
public class SsRopeGradingSystem
{

	/**
	 * Australian rope grading system.
	 */
	public static class Aus
		extends SsGradingSystem
	{

		/**
		 */
		public Aus()
		{
			super("Aus");

			this.addChild("1").addChild("");
			this.addChild("2").addChild("");
			this.addChild("3").addChild("");
			this.addChild("4").addChild("");
			this.addChild("5").addChild("");
			this.addChild("6").addChild("");
			this.addChild("7").addChild("");
			this.addChild("8").addChild("");
			this.addChild("9").addChild("");
			this.addChild("10").addChild("");
			this.addChild("11").addChild("");
			this.addChild("12").addChild("");
			this.addChild("13").addChild("");
			this.addChild("14").addChild("");
			this.addChild("15").addChild("");
			this.addChild("16").addChild("");
			this.addChild("17").addChild("");
			this.addChild("18").addChild("");
			this.addChild("19").addChild("");
			this.addChild("20").addChild("");
			this.addChild("21").addChild("");
			this.addChild("22").addChild("");
			this.addChild("23").addChild("");
			this.addChild("24").addChild("");
			this.addChild("25").addChild("");
			this.addChild("26").addChild("");
			this.addChild("27").addChild("");
			this.addChild("28").addChild("");
			this.addChild("29").addChild("");
			this.addChild("30").addChild("");
			this.addChild("31").addChild("");
			this.addChild("32").addChild("");
			this.addChild("33").addChild("");
			this.addChild("34").addChild("");
			this.addChild("35").addChild("");
			this.addChild("36").addChild("");
			this.addChild("37").addChild("");
			this.addChild("38").addChild("");
			this.addChild("39").addChild("");
		}

	}

	/**
	 * Finnish rope grading system.
	 */
	public static class Fin
		extends SsGradingSystem
	{

		/**
		 */
		public Fin()
		{
			super("Fin");

			this.addChild("1").addChildren("-", "", "+");
			this.addChild("2").addChildren("-", "", "+");
			this.addChild("3").addChildren("-", "", "+");
			this.addChild("4").addChildren("-", "", "+");
			this.addChild("5").addChildren("-", "", "+");
			this.addChild("6").addChildren("-", "", "+");
			this.addChild("7").addChildren("-", "", "+");
			this.addChild("8").addChildren("-", "", "+");
			this.addChild("9").addChildren("-", "", "+");
			this.addChild("10").addChildren("-", "", "+");
			this.addChild("11").addChildren("-", "", "+");
			this.addChild("12").addChildren("-", "", "+");
		}

	}

	/**
	 * French rope grading system.
	 */
	public static class Fre
		extends SsGradingSystem
	{

		/**
		 */
		public Fre()
		{
			super("Fre");

			this.addChild("1").addChildren("-", "", "+");
			this.addChild("2").addChildren("-", "", "+");
			this.addChild("3").addChildren("", "+");
			this.addChild("4").addChildren("a", "b", "c");
			this.addChild("5").addChildren("a", "b", "c");
			this.addChild("6").addChildren("a", "b", "c");
			this.getChild("6").getChild("a").addChildren("", "+");
			this.getChild("6").getChild("b").addChildren("", "+");
			this.getChild("6").getChild("c").addChildren("", "+");
			this.addChild("7").addChildren("a", "b", "c");
			this.getChild("7").getChild("a").addChildren("", "+");
			this.getChild("7").getChild("b").addChildren("", "+");
			this.getChild("7").getChild("c").addChildren("", "+");
			this.addChild("8").addChildren("a", "b", "c");
			this.getChild("8").getChild("a").addChildren("", "+");
			this.getChild("8").getChild("b").addChildren("", "+");
			this.getChild("8").getChild("c").addChildren("", "+");
			this.addChild("9").addChildren("a", "b", "c");
			this.getChild("9").getChild("a").addChildren("", "+");
			this.getChild("9").getChild("b").addChildren("", "+");
			this.getChild("9").getChild("c").addChild("");
		}

	}

	/**
	 * Norway rope grading system.
	 */
	public static class Nor
		extends SsGradingSystem
	{

		/**
		 */
		public Nor()
		{
			super("Nor");

			this.addChild("1").addChildren("-", "", "+");
			this.addChild("2").addChildren("-", "", "+");
			this.addChild("3").addChildren("-", "", "+");
			this.addChild("4").addChildren("-", "", "+");
			this.addChild("5").addChildren("-", "", "+");
			this.addChild("6").addChildren("-", "", "+");
			this.addChild("7").addChildren("-", "", "+");
			this.addChild("8").addChildren("-", "", "+");
			this.addChild("9").addChildren("-", "", "+");
			this.addChild("10").addChildren("-", "", "+");
			this.addChild("11").addChildren("-", "", "+");
			this.addChild("12").addChildren("-", "", "+");
		}

	}

	/**
	 * Saxon rope grading system.
	 */
	public static class Sax
		extends SsGradingSystem
	{

		/**
		 */
		public Sax()
		{
			super("Sax");

			this.addChild("I").addChild("");
			this.addChild("II").addChild("");
			this.addChild("III").addChild("");
			this.addChild("IV").addChild("");
			this.addChild("V").addChild("");
			this.addChild("VI").addChild("");
			this.addChild("VII").addChildren("a", "b", "c");
			this.addChild("VIII").addChildren("a", "b", "c");
			this.addChild("IX").addChildren("a", "b", "c");
			this.addChild("X").addChildren("a", "b", "c");
			this.addChild("XI").addChildren("a", "b", "c");
			this.addChild("XII").addChildren("a", "b", "c");
		}

	}

	/**
	 * UIAA rope grading system.
	 */
	public static class Uiaa
		extends SsGradingSystem
	{

		/**
		 */
		public Uiaa()
		{
			super("Uiaa");

			this.addChild("I").addChild("");
			this.addChild("II").addChild("");
			this.addChild("III").addChild("");
			this.addChild("IV").addChildren("", "+");
			this.addChild("V").addChildren("", "+");
			this.addChild("VI").addChildren("-", "", "+");
			this.addChild("VII").addChildren("-", "", "+");
			this.addChild("VIII").addChildren("-", "", "+");
			this.addChild("IX").addChildren("-", "", "+");
			this.addChild("X").addChildren("-", "", "+");
			this.addChild("XI").addChildren("-", "", "+");
			this.addChild("XII").addChildren("-", "", "+");
			this.addChild("XIII").addChild("-");
		}

	}

	/**
	 * USA rope grading system.
	 */
	public static class Usa
		extends SsGradingSystem
	{

		/**
		 */
		public Usa()
		{
			super("USA");

			this.addChild("5.0").addChild("");
			this.addChild("5.1").addChild("");
			this.addChild("5.2").addChild("");
			this.addChild("5.3").addChild("");
			this.addChild("5.4").addChild("");
			this.addChild("5.5").addChild("");
			this.addChild("5.6").addChild("");

			this.addChild("5.7").addChildren("-", "", "+");
			this.addChild("5.8").addChildren("-", "", "+");
			this.addChild("5.9").addChildren("-", "", "+");

			this.addChild("5.10").addChildren("a", "b", "c", "d", "");
			this.getChild("5.10").getChild("").addChildren("-", "", "+");
			this.addChild("5.11").addChildren("a", "b", "c", "d", "");
			this.getChild("5.11").getChild("").addChildren("-", "", "+");
			this.addChild("5.12").addChildren("a", "b", "c", "d", "");
			this.getChild("5.12").getChild("").addChildren("-", "", "+");
			this.addChild("5.13").addChildren("a", "b", "c", "d", "");
			this.getChild("5.13").getChild("").addChildren("-", "", "+");
			this.addChild("5.14").addChildren("a", "b", "c", "d", "");
			this.getChild("5.14").getChild("").addChildren("-", "", "+");
			this.addChild("5.15").addChildren("a", "b", "c", "d", "");
			this.getChild("5.15").getChild("").addChildren("-", "", "+");
		}

	}

}
