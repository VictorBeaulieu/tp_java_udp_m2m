import java.util.ArrayList;
import java.util.List;

public class Message {
	private List<String> arrayKey = new ArrayList<String>();
	private List<String> arrayValue = new ArrayList<String>();
	
	public String serialize(List<String> arrayKey, List<String> arrayValue) {
		
		String tmp = new String("{\"");
		
		tmp += arrayKey.get(0) + "\":\"" +arrayValue.get(0);		
		for (int i= 1; i< arrayValue.size(); i++) 
		{ 
		    tmp += "\",\"" + arrayKey.get(i) + "\":\"" +arrayValue.get(i)  ;
		}
		tmp += "\"}";
		return tmp;	
	}
	public String deserialize(String message) {
		
		arrayKey.clear();
		arrayValue.clear();
		
		String[] listString;

		if(message.indexOf("{") != -1) {
			if(message.indexOf("}") != -1) {
				message = message.replace("{", "");
				message = message.replace("}", "");
				message = message.replace("\"", "");
				listString = message.split(",");				
				for(int i =0; i< listString.length ; i++)
				{
					if(listString[i].indexOf(":") !=-1) {
						if(listString[i].indexOf(":") == 0) return "missing key";
						if(listString[i].indexOf(":") == listString[i].length()-1) return "missing value";
						arrayKey.add(listString[i].substring(0, listString[i].indexOf(":")));
						arrayValue.add(listString[i].substring(1+listString[i].indexOf(":")));
					}
					else return "missing ':'";
				}	
			}
			else return "missing '}'";
		}
		else return "missing '{'";
		
		return "ok";		
	}
	public List<String> getArrayKey() {
		return arrayKey;
	}
	public List<String> getArrayValue() {
		return arrayValue;
	}

}
