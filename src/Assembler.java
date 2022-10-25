import java.io.*;
import java.util.Scanner;
public class Assembler {

	public static void main(String[] args) {
		//find the file to be read
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a .asm file name:");
		String filename = input.nextLine();
		input.close();
		
		//create the parser and symbol table
		Parser parser = new Parser(filename);
		Parser tablebuilder = new Parser(filename);
		SymbolTable table = new SymbolTable();
		
		//create the output file
		String outputname = filename.substring(0, filename.indexOf(".asm")) + ".hack";
		File output = new File(outputname);
		try
		{
			FileWriter out = new FileWriter(output);
		
			int linenum = 0;
			//parse the instructions
			//create the symbol table
			while (tablebuilder.hasMoreLines())
			{
				tablebuilder.advance();
				if (tablebuilder.instructionType() == "L_INSTRUCTION")
				{
					table.addEntry(tablebuilder.symbol(), linenum);
				}
				else
					linenum++;
			}
			//SECOND PASS
			String symbol;
			String line;
			int ram = 16;
			int address;
			Code cdecoder = new Code();
			while (parser.hasMoreLines())
			{
				line = "";
				parser.advance();
				if (parser.instructionType() == "A_INSTRUCTION")
				{
					symbol = parser.symbol();
					if (!Character.isDigit(symbol.charAt(0)))
					{
						if (table.contains(symbol))
							address = table.getAddress(symbol);
						else
						{
							address = ram;
							table.addEntry(symbol, ram++);
						}
					}
					else
						address = Integer.parseInt(symbol);
					
					line = Integer.toBinaryString(address);
					while (line.length() < 16)
					{
						line = "0" + line;
					}
					line = line + "\n";
				}
				
				else if (parser.instructionType() == "C_INSTRUCTION")
				{
					line = "111" + cdecoder.comp(parser.comp()) + cdecoder.dest(parser.dest()) + cdecoder.jump(parser.jump()) + "\n"; 
				}
				
				out.write(line);
			}
			
			out.close();
		}
		catch (IOException e)
		{
			System.out.println("There was a problem outputting to the file");
			e.printStackTrace();
		}
		
		
		System.out.println("complete");
	}

}
