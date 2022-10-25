import java.util.HashMap;
public class Code {

	String dest(String dest)
	{
		String a = "0";
		String d = "0";
		String m = "0";
		
		if (dest.contains("A"))
			a = "1";
		if (dest.contains("D"))
			d = "1";
		if (dest.contains("M"))
			m = "1";
			
		return (a + d + m);
	}
	
	String comp(String comp)
	{
		//comp = comp.replaceAll("\\s",  ""); //remove all whitespace
		
		String msb = "0";
		if (comp.contains("M"))
		{
			msb = "1";
			comp = comp.replace("M", "A"); //normalize a/m
		}
		HashMap<String, String> possiblities = new HashMap<String, String>();
		possiblities.put("0",   "101010");
		possiblities.put("1",   "111111");
		possiblities.put("-1",  "111010");
		possiblities.put("D",   "001100");
		possiblities.put("!D",  "001101");
		possiblities.put("-D",  "001111");
		possiblities.put("D-1", "001110");
		possiblities.put("A",   "110000");
		possiblities.put("!A",  "110001");
		possiblities.put("-A",  "110011");
		possiblities.put("A-1", "110010");
		possiblities.put("D+1", "011111");
		possiblities.put("A+1", "110111");
		possiblities.put("D+A", "000010");
		possiblities.put("D&A", "000000");
		possiblities.put("D|A", "010101");
		possiblities.put("D-A", "010011");
		possiblities.put("A-D", "000111");
		//given more time, you could probably optimize this using logic on the strings and individually set every flag
		//though I actually don't know if that would be faster or slower. 
			return msb + possiblities.get(comp);
	}
	
	String jump(String jump)
	{
		String greater = "0";
	    String equal = "0";
		String less = "0";
		
		if (jump.contains("M"))
		{
			return "111";
		}
		
		if (jump.contains("N"))
			return "101";
		if (jump.contains("G"))
			greater = "1";
		if (jump.contains("L"))
			less = "1";
		if (jump.contains("E"))
			equal = "1";
		
		return (less + equal + greater);
	}
}
