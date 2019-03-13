package main;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Home 
{
	static WebDriver driver = null;
	static Actions rightClick = null;
	static String lastSquareClicked = "";
	static int vertical;
	static int horizontal;
	
	public static void main(String args[])
	{
		//Method to set up Webdriver
		initialiseDriver();
		
		//Begin the game
		beginGame();
	}
	
	/**
	 * Initializes the Selenium driver and the Minesweeper page
	 */
	public static void initialiseDriver()
	{
		//Set the property of the chromedriver (in base directory of proejct)
		System.setProperty("webdriver.chrome.driver","chromedriver");
		 
		//Create a new Chromedriver
		driver = new ChromeDriver();
		
		//Select the intermediate level minesweeper
		String baseUrl = "http://minesweeperonline.com/#intermediate";
		driver.get(baseUrl);
		
		//Initial Coordinates
		vertical = 1;
		horizontal = 1;
		
		//Create an action to do a right click (flag bomb)
		rightClick = new Actions(driver);	
	}
	
	public static void beginGame()
	{
		//Click first square
		WebElement element = driver.findElement(By.id(vertical + "_" + horizontal));
		element.click();
		
		//Check the initial face value, if it's a loss then stop the application, 
		//otherwise continue iterating through the squares
		WebElement faceElement = driver.findElement(By.id("face"));
				
		if(faceElement.getAttribute("class") == FaceValues.LOSE.toString())
		{
			System.out.println("Unlucky first square was a bomb");
		}
		else
		{
			while(faceElement.getAttribute("class") != FaceValues.WIN.toString())
			{
				iterateSquares();
			}	
		}
	}
	
	
	/**
	 * Iterates through the squares until the game is won
	 */
	public static void iterateSquares()
	{
		//Click square
		WebElement element = driver.findElement(By.id(vertical + "_" + horizontal));
		lastSquareClicked = element.getAttribute("class");
		if(element.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
		{
			setNextSquare();
			element.click();
		}
	    //Get the value of the last square clicked
	   	    
	    //If the last square that was clicked nothing, proceed to the next square
	    if(lastSquareClicked.compareTo(SquareValues.ZERO) == 0 || lastSquareClicked.compareTo(SquareValues.FLAGGED) == 0)
	    {
	    	setNextSquare();
	    }
	    //If it's not zero then we need to check the square
	    else
	    {
			checkSquare();
			setNextSquare();
	    }
	}

	public static void checkSquare()
	{
		WebElement currentElement = null;
		ArrayList<String> blankSquare = new ArrayList<String>();
		ArrayList<String> flaggedSquare = new ArrayList<String>();
		
		if(vertical > 1)
		{
			if(horizontal > 1)
			{
				currentElement = driver.findElement(By.id((vertical-1) + "_" + (horizontal-1)));
				if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
				{
					blankSquare.add((vertical-1) + "_" + (horizontal-1));
				}
				else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
				{
					flaggedSquare.add((vertical-1) + "_" + (horizontal-1));
				}
			
			}
			
			currentElement = driver.findElement(By.id((vertical-1) + "_" + (horizontal)));
			if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
			{
				blankSquare.add((vertical-1) + "_" + (horizontal));
			}
			else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
			{
				flaggedSquare.add((vertical-1) + "_" + (horizontal));
			}
			
			if(horizontal < 16)
			{
				currentElement = driver.findElement(By.id((vertical-1) + "_" + (horizontal+1)));
				if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
				{
					blankSquare.add((vertical-1) + "_" + (horizontal+1));
				}
				else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
				{
					flaggedSquare.add((vertical-1) + "_" + (horizontal+1));
				}
			}
		}
		
		if(horizontal > 1)
		{
			currentElement = driver.findElement(By.id((vertical) + "_" + (horizontal-1)));
			if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
			{
				blankSquare.add((vertical) + "_" + (horizontal-1));
			}
			else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
			{
				flaggedSquare.add((vertical) + "_" + (horizontal-1));
			}
		}
		
		if(horizontal < 16)
		{
			currentElement = driver.findElement(By.id((vertical) + "_" + (horizontal+1)));
			if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
			{
				blankSquare.add((vertical) + "_" + (horizontal+1));
			}
			else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
			{
				flaggedSquare.add((vertical) + "_" + (horizontal+1));
			}
		}
		
		if(vertical < 16)
		{
			if(horizontal > 1)
			{
				currentElement = driver.findElement(By.id((vertical+1) + "_" + (horizontal-1)));
				if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
				{
					blankSquare.add((vertical+1) + "_" + (horizontal-1));
				}
				else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
				{
					flaggedSquare.add((vertical+1) + "_" + (horizontal-1));
				}
			}
			
			currentElement = driver.findElement(By.id((vertical+1) + "_" + (horizontal)));
			if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
			{
				blankSquare.add((vertical+1) + "_" + (horizontal));
			}
			else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
			{
				flaggedSquare.add((vertical+1) + "_" + (horizontal));
			}
			
			if(horizontal < 16)
			{
				currentElement = driver.findElement(By.id((vertical+1) + "_" + (horizontal+1)));
				if(currentElement.getAttribute("class").compareTo(SquareValues.BLANK) == 0)
				{
					blankSquare.add((vertical+1) + "_" + (horizontal+1));
				}
				else if(currentElement.getAttribute("class").compareTo(SquareValues.FLAGGED) == 0)
				{
					flaggedSquare.add((vertical+1) + "_" + (horizontal+1));
				}
			}
		}
		
		if(flaggedSquare.size() == SquareValues.getStringIntValue(lastSquareClicked))
		{
			//iterating ArrayList
		     for(String currentVal :blankSquare)
		     {
		    	 WebElement squaresToPush = driver.findElement(By.id(currentVal));
		    	 squaresToPush.click();
		     } 
		}
		else if((blankSquare.size()+flaggedSquare.size()) == SquareValues.getStringIntValue(lastSquareClicked))
		{
			//iterating ArrayList
		     for(String currentVal :blankSquare)
		     {
		    	WebElement blankElement = driver.findElement(By.id(currentVal));
		    	rightClick.contextClick(blankElement).perform();
		     }  
		}
	}
	
	public static void setNextSquare()
	{
		//Set the coordinates of the next square
    	if(horizontal == 16)
    	{
    		//If all of the squares have been processed but the game isn't won, return back to the start
    		if(vertical == 16)
    		{
    			vertical = 1;
    			horizontal = 1;
    		}
    		else
    		{
    			horizontal = 1;
	    		vertical++;	
    		}	
    	}
    	else
    	{
    		horizontal++;
    		
    	}
    	iterateSquares();
	}
}
