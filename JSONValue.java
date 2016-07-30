import java.util.ArrayList;

public class JSONValue 
{
	String value;
	ArrayList<JSONValue> arr;
	JSONObject ob;
	
	int type; //1 string 2 array 3 object
	
	JSONValue(){
		type=0;
		value="";
		arr=new ArrayList<JSONValue>();
	}

	public String toString()
	{
		//return value.toString();
		if(type == 1)
			return value;
		else if(type==2)
			return arr.toString();
		else if(type == 3)
			return ob.toString();
		else
			return "Ashish"; 
	}
}