package main;

public class SquareValues
{
	final static String BLANK = "square blank";
	final static String ZERO = "square open0";
	final static String ONE = "square open1";
	final static String TWO = "square open2";
	final static String THREE = "square open3";
	final static String FOUR = "square open4";
	final static String FIVE = "square open5";
	final static String SIX = "square open6";
	final static String SEVEN = "square open7";
	final static String EIGHT = "square open8";
	final static String BOMB = "bombrevealed";
	final static String FLAGGED = "square bombflagged";
	
	public static int getStringIntValue(String inputValue)
	{
		switch(inputValue)
		{
			case ZERO:
				return 0;
			case ONE:
				return 1;
			case TWO:
				return 2;
			case THREE:
				return 3;
			case FOUR:
				return 4;
			case FIVE:
				return 5;
			case SIX:
				return 6;
			case SEVEN:
				return 7;
			case EIGHT:
				return 8;
			default:
				return -1;
		}
	}
}

