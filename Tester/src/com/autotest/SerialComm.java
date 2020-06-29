package com.autotest;

import org.eclipse.swt.custom.StyledText;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


class SerialComm
{
    private SerialPort[] comPortArray;
    private SerialPort myPort = null;
    private StyledText consoleLogString;
    private String readData;
    private Test tester;
    private boolean isTest = false;
    
    void setTester(Test tester)
    {
    	this.tester = tester;
    }
    
    boolean getIsTest()
    {
    	return this.isTest;
    }
    
    void setIsTest(boolean isTest)
    {
    	this.isTest = isTest;
    }
    
    boolean Open(int id, int baudRate)
    {
        if(!comPortArray[id].openPort())
            return false;
        //System.out.println("Port is openned!");
        myPort = comPortArray[id];
        myPort.setBaudRate(baudRate);
        myPort.clearRTS();
        myPort.addDataListener(new SerialPortDataListener()
        {
            @Override
            public int getListeningEvents()
            {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event)
            {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                    return;

                if(myPort.bytesAvailable() > 0)
                {	
              	  	readPort();
              	  	printToConsole();
              	  	testInComm();
                }
            }
        });
        return true;
    }
    
    void testInComm()
    {
    	if (tester != null) 
    	{
      	  	tester.configure(readData);
          	try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
          	tester.showResults();
		}
    }
    
    void readPort()
    {
    	StringBuilder sBuilder = new StringBuilder();
    	
    	while(myPort.bytesAvailable() > 0)
    	{
    		byte[] readByte = new byte[1];
    		myPort.readBytes(readByte, 1);
        	sBuilder.append(new String(readByte));
    	}
    	
    	readData = sBuilder.toString();
    }
    
    void printToConsole()
    {
    	consoleLogString.getDisplay().asyncExec(new Runnable ()
		{
			public void run ()
			{	
				consoleLogString.append(readData);
			}
		});
    }
    
    void setConsoleLogString(StyledText consoleLogString) 
    {
		this.consoleLogString = consoleLogString;
	}
    
    SerialPort getPort()
    {
    	return myPort;
    }


    void Close()
    {
        if (myPort != null) 
        {
            myPort.removeDataListener();
            myPort.closePort();
            myPort = null;
            //System.out.println("Port is closed!");
        }
    }

    void writeToPort(byte key)
    {
        if(myPort == null)
            return;
        byte [] buffer = new byte[1];
        buffer[0] = key;
        myPort.writeBytes(buffer, 1);
    }

    void writeToPortString(String text)
    {
        byte[] commandBytes = text.getBytes();
        for (byte commandByte : commandBytes)
            writeToPort(commandByte);
    }

    String[] getCommPortNames()
    {
        comPortArray = SerialPort.getCommPorts();
        String[] comPortNames = new String[comPortArray.length];
        
        for(int i = 0; (i < comPortArray.length) && i < 100; ++i)
            comPortNames[i] = comPortArray[i].getSystemPortName();
        
        return comPortNames;
    }
}