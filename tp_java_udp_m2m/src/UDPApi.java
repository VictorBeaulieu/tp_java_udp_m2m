import java.io.*;
import java.net.*;
import java.util.Date;

class UDPApi
{
	
	public static final int UDPBUFLEN = 1024;

	protected int		Port;
	protected DatagramSocket Socket;
	protected byte[]	MsgBuffer;
	
	protected DatagramPacket ReceivePacket;			
	protected String 	ReceivedMessage;
	protected String 	Source_ip;
	protected int 		Source_port; 
	protected long		ReceiveDuration;

	/**
	 * UDPApi constructor
	 */
	public UDPApi()
	{
		Port = 0;
		Socket = null;
		MsgBuffer = new byte[UDPBUFLEN];
		ReceivePacket = new DatagramPacket(MsgBuffer, UDPBUFLEN);
		ReceivedMessage = "";
		Source_ip = "0.0.0.0";
		Source_port = 0; 
		ReceiveDuration = 0;
	}

	/**
	 * socket opening method
	 * @param port : local port to bind with
	 * @throws exception if port already used
	 */
	public void open(int port) throws UDPApiException
	{
		// check status
		if (Port > 0)
			throw new UDPApiException("socket already active");
		
		// open socket
		Port = port;

		try{
			Socket = new DatagramSocket(Port);
		}
		catch(SocketException e){
			throw new UDPApiException(e.getMessage());
		}
			
	}

	/**
	 * datagram sending
	 * @param message : string with latin-1 characters 
	 * @param dest_ip : string destination IP adress 
	 * @param dest_port : destination port
	 * @throws exception if format error on message or anything wrong for sending
	 */
	public void send(String message, String dest_ip, int dest_port) throws UDPApiException
	{
		
		try {
			//destination address parameter
			InetAddress wIPAddress = InetAddress.getByName(dest_ip);
		
			//message byte buffer encoding
			MsgBuffer = message.getBytes("ISO-8859-1");
		
	      		//send
	      		DatagramPacket wSendPacket = new DatagramPacket(MsgBuffer, MsgBuffer.length, wIPAddress, dest_port);
	      		Socket.send(wSendPacket);
		}

		catch(UnknownHostException e){
			throw new UDPApiException("bad destination address");
		}
		catch(UnsupportedEncodingException e){
			throw new UDPApiException("bad message encoding : " + e.getMessage());
		}
		catch(IOException e){
			throw new UDPApiException("error datagram sending : " + e.getMessage());
		}
		
	}

	/**
	 * datagram receiving
	 * @param receiveTimeout (value in msec for blocking reception)
	 * @return true if received something, false if time-out
	 * @throws exception if format error on message or anything wrong for sending
	 * message,source_ip and source_port are filled (must be retrieved by getters)
	 */
	public boolean receive(int receiveTimeout) throws UDPApiException
	{
		
		Date wDateStart= new Date();
         	long wtpstart = wDateStart.getTime();
			
		try {
			//init parameters			
			ReceiveDuration = 0;
			if (receiveTimeout > 0) 
				Socket.setSoTimeout(receiveTimeout);
			
			
			//waiting for datagram
         		Socket.receive(ReceivePacket);
			
			//read data structure
			ReceivedMessage = new String( ReceivePacket.getData(), 0, ReceivePacket.getLength(), "ISO-8859-1");
			InetAddress wIPAddress = ReceivePacket.getAddress();
			Source_ip = wIPAddress.getHostAddress();
			Source_port = ReceivePacket.getPort();
			
			//calculate operation duration
			setReceiveDuration(wtpstart);
					
				
		}
		catch(SocketTimeoutException e){
			//calculate operation duration
			setReceiveDuration(wtpstart);
			return false;
		}
		catch(SocketException e){
			throw new UDPApiException("time-out setting error");
		}
		catch(IOException e){
			//calculate operation duration
			setReceiveDuration(wtpstart);
			throw new UDPApiException("error datagram receiving : " + e.getMessage());
		}
	
		return true;
	}


	/**
	 * last reception getters
	 */
	public String getReceivedMessage()
	{
		return ReceivedMessage;
	}

	public String getSource_ip()
	{
		return Source_ip;
	}

	public int getSource_port()
	{
		return Source_port;
	}

	public long getReceiveDuration()
	{
		return ReceiveDuration;
	}


	/**
	 * calculation of Receive duration operation
	 */
	protected void setReceiveDuration(long tpstart)
	{
		Date wDateEnd= new Date();
         	long wtpend = wDateEnd.getTime();
		ReceiveDuration = wtpend - tpstart;	
	}

	/**
	 * socket close method
	 * @throws exception if already closed
	 */
	public void close() throws UDPApiException
	{
		// check status
		if (Port == 0)
			throw new UDPApiException("socket already closed");
		
		Port = 0;
		Socket.close();
	}

}

