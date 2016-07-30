import java.io.*;
import java.util.*;
import url.*;

public class JSONParser{

	Tokenizer t;
	static JSONObject job;
	static ArrayList<JSONObject> lis;

	JSONParser(String a){
		t=new Tokenizer(a);
		job=new JSONObject();
		lis=new ArrayList<JSONObject>();
	}

	public void parser(){
		Token token=t.getToken();
		if(token.type==1){
			job=getJSONObject();
			lis.add(job);
		}
		else{ 
			if(token.type==3){
			//HAS TO BE OBJECT                    will check others later
				token=t.getToken();
				while(token.type!=4){
					JSONObject temp=new JSONObject();
					temp=getJSONObject();
					lis.add(temp);
					if(t.lookahead()==','){
 						token=t.getToken();
 					}
 					token=t.getToken();           //Consume } or {
 				}
 			}	
			else{
				System.out.println("Unhandled Syntax");
			}
		}
	}

	public void extractKeyValuePair(JSONObject j){
 		JSONValue jv=new JSONValue();
 		String key=new String("");

 		Token token;
 		//Key
 		token=t.getToken();
 		if(token.type==5){  //Its a string
 			key=token.name;
 		}
 		else{
 			System.out.println("String Expected");
 		}

 		//Colon
 		token=t.getToken();
 		if(token.type==7){  //Its a colon

 		}
 		else{
 			System.out.println("Colon Expected");
 		}

 		//Value
 		token=t.getToken();
 		if(token.type==5){  //Value is a string	
 			jv.value=token.name;
 			jv.type=1;
 		}
 		else if(token.type==3){   //Value is Array.  Array is coming
 			Token tok=t.getToken();
 			
 			while(tok.type!=4){   //while ']' not comes
 				JSONValue jsv=new JSONValue();
 				JSONObject temp=new JSONObject();
 				String name;
 				switch(tok.type){
 					case 5:name=tok.name;         //String
 						   jsv.value=name;
 						   jsv.type=1;
 						   jv.arr.add(jsv);
 						   break;
 					case 1:temp=getJSONObject();  //Object
 							jsv.ob=temp;
 							jsv.type=3;
 						   jv.arr.add(jsv);
 						   break;
 					//Case for Array   will complete later
 					//////////////////////////////////////   
 				}
 				if(t.lookahead()==','){
 					tok=t.getToken();
 				}
 				tok=t.getToken();			
 			}
 			jv.type=2;
 		}
 		else if(token.type==1){               //Value is JSONObject
 			jv.ob=getJSONObject();
 			jv.type=3;
 		}
 		else{
 			System.out.println("Syntax Error");
 		}
 		//Add KEY VALUE pair
 		j.add(key,jv);
 		//System.out.println();
	}

 	public JSONObject getJSONObject(){
 		JSONObject js=new JSONObject(); 
 		//Extracting key and value pair
 		Token tok;
 		extractKeyValuePair(js);
 		while(t.lookahead()!='}'){
 			if(t.lookahead()==','){
 				tok=t.getToken();          //Consuming ,
 				extractKeyValuePair(js);
 			}
 			else{
 				System.out.println("Error");
 				System.exit(0);
 			}
 		}
 		tok=t.getToken(); //Consuming }
 		return js;
 	}

	public static void main(String[] args){
		String jsonstring="";
		String place="";
		/*try{
			jsonstring = new Scanner(new File("ex.txt")).useDelimiter("\\Z").next();
			System.out.println(jsonstring);
			//content.trim();			
		}
		catch(Exception e){
        	e.printStackTrace();
      	}*/
      	Scanner in=new Scanner(System.in);
      	System.out.print("Enter Place:");
      	place=in.nextLine();
      	Urllib u=new Urllib();

      	StringBuffer sb = u.urlopen("http://maps.googleapis.com/maps/api/geocode/json?sensor=false&address="+place);
     
      	System.out.println(jsonstring);
		jsonstring=sb.toString().replaceAll("\\s+","");		

      	JSONParser jp=new JSONParser(jsonstring);

     	jp.parser();

     	System.out.println("Longitude :"+lis.get(0).get("results").arr.get(0).ob.get("geometry").ob.get("location").ob.get("lng"));
     	System.out.println("Latitude :"+lis.get(0).get("results").arr.get(0).ob.get("geometry").ob.get("location").ob.get("lat"));
     	
	}
}