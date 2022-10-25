import java.io.*;
import java.util.Scanner;

public class Parser {
	File file = null;
	Scanner reader = null;
	String instruction = null;
	
	Parser(String filename)
	{
		try 
		{
			file = new File(filename);
			reader = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File could not be found");
			e.printStackTrace();
		}
	}
	
	boolean hasMoreLines()
	{
		return reader.hasNextLine();
	}
	
	void advance()
	{
		String inst = reader.nextLine();
		if (inst == "" || inst.startsWith("//")) //skip whitespace and comments
		{
			if (reader.hasNextLine())
				advance();
			else
				instruction = null;
		}
		else
			instruction = inst;
		
		if (instruction.contains("//"))
		{
			instruction = instruction.substring(0, instruction.indexOf("//"));
		}
		instruction = instruction.replaceAll("\\s",  ""); //remove all whitespace
		//System.out.println(instruction);
	}
	
	String instructionType()
	{
		if (instruction.startsWith("@"))
			return "A_INSTRUCTION";
		else if (instruction.startsWith("(") && instruction.endsWith(")"))
			return "L_INSTRUCTION";
		else if (instruction.contains("=") || instruction.contains(";"))
			return "C_INSTRUCTION";
		else
			return "";
	}
	
	String symbol()
	{
		if (instruction.startsWith("@"))
		{
			return instruction.substring(1);
		}
		else if (instruction.startsWith("(") && instruction.endsWith(")"))
		{
			return instruction.substring(1, (instruction.length() - 1));
		}
		else
			return "";
	}
	
	String dest()
	{
		if (instruction.contains("="))
		{
			return instruction.substring(0, instruction.indexOf("="));
		}
			return "";
	}
	
	String comp()
	{
		if (instruction.contains("=") && instruction.contains(";"))
		{
			return instruction.substring(instruction.indexOf("=") + 1, instruction.indexOf(";"));
		}
		else if (instruction.contains("="))
		{
			return instruction.substring(instruction.indexOf("=") + 1);
		}
		else if (instruction.contains(";"))
		{
			return instruction.substring(0, instruction.indexOf(";"));
		}
		else
			return "";
	}
	
	String jump()
	{
		if (instruction.contains(";"))
		{
			return instruction.substring(instruction.indexOf(";") + 1);
		}
		else
			return "";
	}
}
