import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

class sampleserver
{
	public static void main(String args[]) throws Exception
	{
		//init parameters	
		int localPort = 8889;
		UDPApi myApi = new UDPApi();
		Message msg = new Message();
		List<String> arrayKey = new ArrayList<String>();
		List<String> arrayValue = new ArrayList<String>();
		
		
		System.out.println("SERVER UDP");
		
		if (args.length < 1) {
			System.err.println("usage is sampleserver <local_port>");
			System.exit(1);
		}
		try {
			localPort = Integer.parseInt(args[0]);
				
		} catch (NumberFormatException e) {
			System.err.println("argument " + args[0] + " must be an integer.");
			System.exit(1);
		}
		
		try {
			System.out.println("try to open on port " + Integer.toString(localPort));
			myApi.open(localPort); //open a socket with localPort
		}
		catch(Exception e)
		{
			System.err.println("failled to open socket");
		}
		System.out.println("port openned on " + Integer.toString(localPort));


		try {
			if(myApi.receive(10000)) {
				System.out.println(myApi.getReceivedMessage());
				String error = msg.deserialize(myApi.getReceivedMessage());
				System.out.println(error);
				if(error == "ok") {
					arrayKey.add("errorCode");
						
					if(msg.getArrayKey().contains("lastName")) {
						if(msg.getArrayKey().contains("firstName")) {
							if(msg.getArrayKey().contains("age")) {
								if(msg.getArrayKey().contains("gender")) {
									arrayValue.add("0");
								}
								else{
									arrayValue.add("-2");
									arrayKey.add("errorMsg");
									arrayValue.add("missing gender");
								}
							}
							else {
								arrayValue.add("-2");
								arrayKey.add("errorMsg");
								arrayValue.add("missing age");
							}
						}
						else{
							arrayValue.add("-2");
							arrayKey.add("errorMsg");
							arrayValue.add("missing first name");
						}
					}
					else {
						arrayValue.add("-2");
						arrayKey.add("errorMsg");
						arrayValue.add("missing last name");
					}
					
				}
				else
				{					
					arrayKey.add("errorCode");
					if((error.indexOf("{")!=-1) || (error.indexOf("{")!=-1))arrayValue.add("-1");
					else arrayValue.add("-2");
					arrayKey.add("errorMsg");
					arrayValue.add(error);
																	
				}
				myApi.send(msg.serialize(arrayKey, arrayValue), myApi.Source_ip, myApi.Source_port); // reply to client to ack
				//String tmp = "{\"errorCode\":\"-2\",\"errorMsg\":\"missing last name\"}";
				//String tmp = "{\"errorCode\":\"-2\",\"errorMsg\":\"missing last name\"}";
				//myApi.send(tmp, myApi.Source_ip, myApi.Source_port); // reply to client to ack
			}
			else System.out.println("no message");
		}
		catch(Exception e)
		{
			System.err.println("failled to receice message");
		}
		try {
			myApi.close(); // close socket
			System.out.print("socket closed");
			
		}
		catch(Exception e)
		{
			System.err.println("failled to close, blyat"); 
		}
			
		
	}
}

