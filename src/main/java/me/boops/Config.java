package me.boops;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.JSONObject;

public class Config {
	
	private String customer_key;
	private String customer_secret;
	private String token;
	private String token_secret;
	
	public String getCustomerKey(){
		return this.customer_key;
	}
	
	public String getCustomerSecret(){
		return this.customer_secret;
	}
	
	public String getToken(){
		return this.token;
	}
	
	public String getTokenSecret(){
		return this.token_secret;
	}
	
	public Config() {
		
		StringBuilder sb = new StringBuilder();
		
		try{
			
			// Read The Config File
			BufferedReader br = new BufferedReader(new FileReader("config.json"));
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			
		} catch (Exception err){
			System.out.println(err);
		}

		// Parse The Config File
		JSONObject config = new JSONObject(sb.toString());

		// Write options to strings File if they are set
		this.customer_key = config.getString("customer_key");
		this.customer_secret = config.getString("customer_secret");
		this.token = config.getString("token");
		this.token_secret = config.getString("token_secret");
	}
	
}
