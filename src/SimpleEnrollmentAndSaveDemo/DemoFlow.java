package SimpleEnrollmentAndSaveDemo;
import gnu.io.*;
import java.io.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.IOException;

/**
 * This class provides the utilities to read the data exchanged via USB port.
 */
public class DemoFlow implements SerialPortEventListener {

	/**
	 * Stream for the storage of incoming data
	 */
	private InputStream inputStream;
	/**
	 * Stream for the dispatching of data
	 */
	private OutputStream outputStream;
	/**
	 * Timeout of the USB port
	 */
	private final int PORT_TIMEOUT = 2000;
	/**
	 * Representation of the serial port used for the communication
	 */
	private SerialPort serialPort;
	/**
	 * Buffer that stores the received bytes from the media
	 */
	protected LinkedBlockingQueue<Byte> receivedBytes;
	boolean saveTemplate = false;
	boolean fromForntEnd = false;
	String targetFileName;
	
	/**
	 * Builds a new manager for the communication via USB port.
	 * @exception IOException if an error occurred during the opening of the USB port
	 */
	public DemoFlow(boolean x, String port) throws IOException {
	  receivedBytes = new LinkedBlockingQueue<Byte>(100000);
		//String port = "COM8"; //place the right COM port here, OS dependent
	
		//Check that the USB port exists and is recognized:
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		boolean portFound = false;
		CommPortIdentifier portId = null;
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
		    if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
		    	//System.out.println.info(portId.getName());
				if (portId.getName().equals(port)) {
					System.out.println("Found port: " + port);
					//System.out.println(portId.PORT_SERIAL);
					if(portId != null){
						System.out.println("carries value");
						//System.out.println(portId);
					}
				    portFound = true;
				    break;
				} 
		    } 
		} 
	
		if (!portFound) 
		    throw new IOException("port " + port + " not found.");
	
		try {
			System.out.println("USB port opening...");
		    serialPort = (SerialPort) portId.open("USBCommunicator", PORT_TIMEOUT);
		    System.out.println("USB port opened");
		    inputStream = serialPort.getInputStream();
		    outputStream = serialPort.getOutputStream();
		    serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
			//#==================================================================#
			// WARNING! - DO NOT SET THE FOLLOWING PROPERTY WITH RXTX LIBRARY, IT
			// 			  CAUSES A PROGRAM LOCK:
			// 	serialPort.notifyOnOutputEmpty(true);
			//#==================================================================#
			    	
		    //wait for a while to leave the time to javax.comm to
		    //correctly configure the port:
		    Thread.sleep(1000);
		    
			int baudRate = 115200; //set propertly
	    	serialPort.setSerialPortParams(baudRate, 
	    		SerialPort.DATABITS_8, 
	    		SerialPort.STOPBITS_1, 
				SerialPort.PARITY_NONE);
		    
	    	serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
	    	saveTemplate = false;
		    System.out.println("setted SerialPortParams");
		    		    
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	
	public void closeUSB(){
		//close the streams for serial port:
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			System.err.println("Cannot close streams:" + e.getMessage());
		}
	}

	/**
	 * Listener for USB events
	 * 
	 * @param event new event occurred on the USB port
	 */
	public void serialEvent(SerialPortEvent event){
		switch (event.getEventType()) {
	
			case SerialPortEvent.BI:
			case SerialPortEvent.OE:
			case SerialPortEvent.FE:
			case SerialPortEvent.PE:
			case SerialPortEvent.CD:
			case SerialPortEvent.CTS:
			case SerialPortEvent.DSR:
			case SerialPortEvent.RI:
			case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				//nothing to do...
				//System.out.pri
			    break;
	
			case SerialPortEvent.DATA_AVAILABLE:
				byte received = -1;
				System.out.println("Starting reading data");
				int count = 0;
				boolean withinStream = true;
				int limit = 23;
				if(saveTemplate)
					limit = 509;
				do {
					try {
						received = (byte)inputStream.read();						
					} catch (IOException e) {
						System.err.println("Error reading USB:" + e.getMessage());
					}
				
					synchronized (receivedBytes) {
						try {
							if(saveTemplate){
								count++;
								receivedBytes.add(received);
							}
							else if(received != -1){
								count++;
								receivedBytes.add(received);								
							}
							else if(withinStream){
								count++;
								receivedBytes.add(received);
								withinStream = false;
							}
								
						} catch (IllegalStateException ew) {
							System.err.println(ew.getMessage());
							receivedBytes.poll();
							receivedBytes.add(received);
						}
					}					
				} while( count <= limit);//while(received != -1);
				count = 0;
				withinStream = false;
				//saveTemplate = false;
				//System.out.println(new String(receivedBytes.toArray()));
				String response = receivedBytes.toString();
				System.out.println("Received bytes- " + receivedBytes.size() + saveTemplate);

				//String[] byteValues = response.substring(1, response.length() - 1).split(",");
				
				byte[] bytes = new byte[receivedBytes.size()];//new byte[byteValues.length];
				
				int i = 0;
				while (i < limit+1) {
					bytes[i] = receivedBytes.remove(); //do what you want to with it
					i++;
				}
				//System.out.println("count " + count);
				//System.out.println("value of i- " + i);
				printResponse(bytes);
				//for read template
				System.out.println("number of bytes " + bytes.length);
				if(bytes.length == 510){
					/*for ( i=0; i< bytes.length; i++) {
						if(i == 24)
							System.out.println("prefix ended");
						if(i == 32)
							System.out.println("template starting");
						if(i >= 32 && i <= 532)
						Utilities.byteToStringConversion(bytes[i]);					
					}*/
					System.out.println("saving to file");
					saveTemplate = false;
					try {
						byteArrayToFile(bytes);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("error while writing to file");
					}
					
				}
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_TEMPLATE_EXISTS_FOR_ONE) ){
					System.out.println("\nFP exists at given roll");
				}
				
				//enrollment conditions
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_BAD_FP_QUALITY)){
					System.out.println("\nBad Quality fp");
				}
				else if(matchResponse(bytes, Constants.RESPONSE_ASKING_FOR_FIRST_FP_ENROLL_AT_ONE)){
					System.out.println("\nPress finger for first time");
				}
				else if(matchResponse(bytes, Constants.RESPONSE_ASKING_FOR_SECOND_FP_ENROLL_AT_ONE)){
					System.out.println("\nPress finger for second time");
					//this.sendFpCancelCommand();
				}
				else if(matchResponse(bytes, Constants.RESPONSE_ASKING_FOR_THIRD_FP_ENROLL_AT_ONE)){
					System.out.println("\nPress finger for third time");
				}
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_FP_ACCEPTED_ENROLL_AT_ONE)){
					System.out.println("\nfp accepted follow next instruction");
				}
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_ENROLL_FINISHED_AT_ONE)){
					System.out.println("\nenrollment done at one");
				}				
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_FAILED_TO_GENERALIZE)){
					System.out.println("\nfailed to generalize, try again");
				}
				
				// clear/delete template conditions
				else if(matchResponse(bytes, Constants.RESPONSE_SUCCESS_FOR_CLEAR_TEMPLATE_AT_ONE)){
					System.out.println("\ntemplated cleared");
					if(fromForntEnd){
						Enroll();
						this.fromForntEnd = false;
					}
				}						
				else if(matchResponse(bytes, Constants.RESPONSE_FAILURE_FOR_CLEAR_TEMPLATE_AT_ONE)){
					System.out.println("\ntemplate doesn't exist at one");
					if(fromForntEnd){
						Enroll();
						this.fromForntEnd = false;
					}
				}	
				
				// fp cancel conditions
				else if(matchResponse(bytes, Constants.RESPONSE_FIRST_FOR_FP_CANCEL)){
					System.out.println("\nfirst response after fp cancel");
				}
				else if(matchResponse(bytes, Constants.RESPONSE_SECOND_FOR_FP_CANCEL)){
					System.out.println("\nsecond response after fp cancel");
				}
				
				// led on-off conditions
				else if(matchResponse(bytes, Constants.RESPONSE_FOR_LED_ON_OFF)){
					System.out.println("\nled on-off response found");
				}
				
				// read template conditions
				else if(matchResponse(bytes, Constants.RESPONSE_SUCCESS_FOR_READ_TEMPLATE_AT_ONE)){
					System.out.println("\ntemplate exists, read it and save.");
					saveTemplate = true;
				}
				else if(matchResponse(bytes, Constants.RESPONSE_FAILURE_FOR_READ_TEMPLATE_AT_ONE)){
					System.out.println("\ncouldn't clear, template doesn't exist");
				}
				// break for data available case
			    break;
		}
	}

	protected void write(byte[] buffer){
	    try {
	    	outputStream.write(buffer);
	    	//outputStream.flush();
	    	//System.out.println("done writing 'b'");
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
	}
	
	protected void sendFpCancelCommand(){
		try {
	    	outputStream.flush();
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
		
		for (int i = 0; i < Constants.AT_COMMAND_FOR_FP_CANCEL.length; i++) {
            byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_FOR_FP_CANCEL[i]);
            this.write(data);
        }
	}
	
	protected void printResponse(byte[] bytes){
		for(int i = 0; i < bytes.length; i++){
			System.out.print(Utilities.byteToStringConversion(bytes[i]) + " - ");
		}		
	}
	
	protected boolean matchResponse(byte[] bytes, String[] someResponse){
		for(int i = 0; i< bytes.length; i++){
			if(!Utilities.byteToStringConversion(bytes[i]).trim().equals(someResponse[i].substring(2))){
				return false;
			}
		}
		return true;
	}
	
	protected void LedOnOff(){
		System.out.println("inside LedOnOff");
		try {
	    	outputStream.flush();
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
		for (int i = 0; i < Constants.AT_COMMAND_LED_ON.length; i++) {
	        byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_LED_ON[i]);
	        this.write(data);
	    }
	    
	    try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    for (int i = 0; i < Constants.AT_COMMAND_LED_OFF.length; i++) {
	        byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_LED_OFF[i]);
	        this.write(data);
	    }
	}
	
	protected void DeleteIfNeeded(){
		System.out.println("inside DeleteIfNeeded");
		try {
	    	outputStream.flush();
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
		for (int i = 0; i < Constants.AT_COMMAND_FOR_CLEAR_TEMPLATE_AT_ONE.length; i++) {
            byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_FOR_CLEAR_TEMPLATE_AT_ONE[i]);
            this.write(data);
        }
	}
	
	protected void Enroll(){
		System.out.println("inside Enroll");
		try {
	    	outputStream.flush();
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
		for (int i = 0; i < Constants.AT_COMMAND_FOR_ENROLL_AT_ONE.length; i++) {
            byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_FOR_ENROLL_AT_ONE[i]);
            this.write(data);
        }		
	}
	
	protected void EnrollFromFrontEnd(){
		this.fromForntEnd = true;
		DeleteIfNeeded();
	}
	
	protected void SaveInFile(String targetFileName){
		this.targetFileName = targetFileName;
		System.out.println("inside SaveInFile- " + targetFileName);
		try {
	    	outputStream.flush();
	    } catch (IOException e) {
	    	System.err.println("Cannot write:" + e.getMessage());
	    }
		 for (int i = 0; i < Constants.AT_COMMAND_FOR_READ_TEMPLATE_AT_ONE.length; i++) {
	        byte[] data = Utilities.hexConversion(Constants.AT_COMMAND_FOR_READ_TEMPLATE_AT_ONE[i]);
	        this.write(data);
	    }
	}
	
	protected void byteArrayToFile( byte[] bytes) throws IOException {
		File file = new File(targetFileName.replace("\\", "/"));		
		FileOutputStream stream = new FileOutputStream(file.getAbsolutePath());
		try {
		    stream.write(Arrays.copyOfRange(bytes, 10, 507));
		} finally {
		    stream.close();
		}
		DeleteIfNeeded();
	 }
	
	/*public static void main(String[] args){
		try {
			DemoFlow instance = new DemoFlow(true, "COM8");
			
			//instance.LedOnOff();
			
			instance.DeleteIfNeeded();
			//instance.Enroll();
			//instance.SaveInFile("C:\\Users\\Cyrus\\Desktop\\mbnh7.fpt");
			//instance.write("b".getBytes());
			//instance.targetFileName = "C:\\Users\\Cyrus\\Desktop\\bnh7.fpt";
			//System.out.println(instance.targetFileName.replace("\\", "/"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}