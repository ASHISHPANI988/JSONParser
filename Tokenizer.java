import java.util.*;
import java.io.*;

class Token{
	String name;
	int type;
	Token(String n,int t){
		name=n;
		type=t;
	}
	Token(char ch,int t){
		name=new String(""+ch);//hnew StringBuilder(new String(ch));
		type=t;
	}
}

public class Tokenizer{

	String content;
	static int i;

	Tokenizer(String cont){
		i=0;
		content=cont.replaceAll("\\s+","");
		//System.out.println(content);
	}

	public Token getToken(){
		char ch;
		ch = content.charAt(i);
		String list="123456789.0";
		String s=ch+"";		
		//for(char ch:content.toCharArray()){
		//for(; i < content.length(); i++){		    
		    ++i;
			//System.out.print(ch);
			switch(ch){
				case '{':return new Token(ch,1);
				case '}':return new Token(ch,2);
				case '[':return new Token(ch,3);
				case ']':return new Token(ch,4);
				case '"': s="";
						  while(content.charAt(i)!='"'){
						  	//s.append(content.charAt(i));
						  	s+=content.charAt(i);
						  	++i;
						  }
						  ++i;//crossing quote
						  return new Token(s,5);
				case ',':return new Token(ch,6);
				case ' ':return new Token(ch,8);//break;
				case ':':return new Token(ch,7);
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '.':
				case '6':
				case '7':
				case '8':
				case '9':
				case '0':s="";--i;
						  while(list.contains(content.charAt(i)+"") ){
						  	//s.append(content.charAt(i));
						  	s+=content.charAt(i);
						  	++i;
						  }
						  //++i;//crossing quote
						  return new Token(s,5);

				default: return new Token(ch,-1);						
			}			
	}

	public char lookahead(){
		return content.charAt(i);
	}
	/*
	public static void main(String[] args){
		//Tokenizer t=new Tokenizer("js.text");
		
		String jsonstring="";
		try{
			jsonstring = new Scanner(new File("js.txt")).useDelimiter("\\Z").next();
			//content.trim();			
		}
		catch(Exception e){
        	e.printStackTrace();
      	}

      	Tokenizer t=new Tokenizer(jsonstring);

		System.out.println();
		Token token;
		String name="";
		int count=t.content.length();
		try{
			while(count>0){
				token=t.getToken();
				sSystem.out.println("Type:"+token.type+"   Token:"+token.name);
				--count;
			}
		}
		catch(Exception e){			
		}

	}
	*/
}