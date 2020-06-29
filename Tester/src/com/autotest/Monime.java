package com.autotest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.ibm.icu.text.SimpleDateFormat;

public class Monime extends Test
{
	Monime(SerialComm comm) 
	{
		super(comm);
		this.testCommands.add("selfTest");
		csvOutputFileName = "Monime_Test_SerialNumbers.csv";
	}
	
	private void configGetSetOperations()
	{
		comm.writeToPortString(configSetLetta + lettaSerialNo);
		comm.writeToPortString("\n");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		comm.writeToPortString(configSetCompany + companySerialNo);
		comm.writeToPortString("\n");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		comm.writeToPortString(configGetLetta);
		comm.writeToPortString("\n");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		comm.writeToPortString(configGetCompany);
		comm.writeToPortString("\n");
	}
	
	private void printToCSV() 
	{
		try {
			if (!new File(csvOutputFileName).exists()) {
				new File(csvOutputFileName).createNewFile();
			}
			BufferedReader csvReader = new BufferedReader(new FileReader(csvOutputFileName));
			FileWriter writer = new FileWriter(csvOutputFileName, true);
			StringBuilder sb = new StringBuilder();
			SimpleDateFormat formatter= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());

			if(csvReader.readLine() == null)
			{
				sb.append("Letta Serial No");
				sb.append(',');
				sb.append("Sercair Serial No");
				sb.append(',');
				sb.append("Test Date");
				sb.append('\n');
			}
			sb.append(lettaSerialNo);
			sb.append(",");
			sb.append(companySerialNo);
			sb.append(",");
			sb.append(formatter.format(date));
			sb.append("\n");
			writer.write(sb.toString());
			writer.flush();
			
			String readString = "", temp = csvReader.readLine();
			while( temp != null)
			{
				readString = temp;
				temp = csvReader.readLine();
			};

			JOptionPane.showMessageDialog(null, "Dosyaya yazýlacaklar : " + sb.toString() + "\nDosyaya yazýlanlar : " + readString,"Save to CSV!", JOptionPane.INFORMATION_MESSAGE,new ImageIcon("icons/tick.png"));			
			
			csvReader.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	void test() 
	{
		if (comm.getPort() != null && comm.getPort().isOpen())
		{
			if (getNext()) 
			{
				comm.writeToPortString(this.testCommands.get(getNextIndex())+"\n");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else
			{
				//System.out.println("Test All!!!");
				for (String command : this.testCommands)
				{
					//System.out.println(command);
					comm.writeToPortString(command);
					comm.writeToPortString("\n");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Port is not available!","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	void configure(String readData) 
	{
		if (comm.getIsTest()) 
		{
			isScannable = readData.contains("{TESTOK}");
			comm.setIsTest(!readData.contains("TestResult:{"));
		}
	}
	
	@Override
	void showResults()
	{
		if (isScannable) 
		{
			String serialNo = (String)JOptionPane.showInputDialog(null,"Enter Letta Serial Number!","Letta Serial No",JOptionPane.QUESTION_MESSAGE);

			if (serialNo != null && serialNo.length() > 0) 
			{
				int confirmLetta = JOptionPane.showConfirmDialog(null, "Letta Serial No : " + serialNo,"Letta Serial No",JOptionPane.INFORMATION_MESSAGE);
				
				if (confirmLetta == JOptionPane.OK_OPTION) 
				{
					lettaSerialNo = serialNo;
					serialNo = (String)JOptionPane.showInputDialog(null,"Enter Sercair Serial Number!","Sercair Serial No",JOptionPane.QUESTION_MESSAGE);
					if (serialNo != null && serialNo.length() > 0)
					{
						int confirmSercair = JOptionPane.showConfirmDialog(null, "Sercair Serial No : " + serialNo,"Sercair Serial No",JOptionPane.INFORMATION_MESSAGE);
						
						if (confirmSercair == JOptionPane.OK_OPTION) 
						{
							companySerialNo = serialNo;
							configGetSetOperations();
							printToCSV();
							String messageString = "Letta Serial Number : " + lettaSerialNo + "\nSercair Serial Number : " + companySerialNo;
							
							JOptionPane.showMessageDialog(null, messageString, "Success!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("icons/tick.png"));
							
						}
						else 
						{
							lettaSerialNo = null;
						}
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Unacceptable format is entered for Sercair Serial No!", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Unacceptable format is entered for Letta Serial No!", "Error!", JOptionPane.ERROR_MESSAGE);
			}
			isScannable = false;
		}
	}
}
