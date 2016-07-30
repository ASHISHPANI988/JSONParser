import java.util.*;

class JSONObject{
	HashMap<String,JSONValue> kv;
	
	JSONObject(){
		kv=new HashMap<String,JSONValue>();
	}
	
	public void add(String name,JSONValue jv){
		kv.put(name,jv);
	}

	public JSONValue get(String key) 
	{
		return kv.get(key);
	}
	
	public String toString()
	{
		String res = "{\n";
		Iterator<HashMap.Entry<String, JSONValue>> it = kv.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String, JSONValue> ob = it.next(); 
			res = res+ob.getKey()+":"+ob.getValue().toString()+"\n";
		}
		return res+"}\n";
	}	
}
