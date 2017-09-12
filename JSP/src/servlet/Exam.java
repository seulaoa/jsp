package servlet;

import java.util.HashMap;

import com.google.gson.Gson;

public class Exam {
	
	public static void main(String[] args) {
		String str = "{\"key1\":\"value1\", \"key2\":\"value2\"}";
		Gson g = new Gson();
		HashMap<String,String> hm = g.fromJson(str, HashMap.class);
		System.out.println(hm);
		 
	}
}
