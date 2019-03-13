package main;

public enum FaceValues
{
	ACTIVE("faceactive"),
	WIN("facewin"),
	LOSE("facedead");
	
	private String faceValue;
	
	FaceValues(String inputValue) 
	{
        this.faceValue = inputValue;
    }

    public String faceValue() 
    {	
        return faceValue;
    }
}