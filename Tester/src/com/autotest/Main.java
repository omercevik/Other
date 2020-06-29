package com.autotest;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

class Main 
{
	protected Shell shlLettaTerminal;
	private SerialComm comm;
	private StringBuilder line;
	private boolean isPortOpen = false;
	private Test testerObj;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try {
			Main window = new Main();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLettaTerminal.open();
		shlLettaTerminal.layout();
		while (!shlLettaTerminal.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLettaTerminal = new Shell(SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shlLettaTerminal.setBackground(SWTResourceManager.getColor(51, 51, 51));
		
		/**
		 * When the application window is closed then close the open ports.
		 */
		shlLettaTerminal.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				if (isPortOpen)
					comm.Close();
			}
		});
		shlLettaTerminal.setText("Letta Test Terminal");
		shlLettaTerminal.setSize(1094, 769);
		shlLettaTerminal.setLayout(new FormLayout());
		Rectangle screenSize = Display.getDefault().getPrimaryMonitor().getBounds();
		shlLettaTerminal.setLocation((screenSize.width - shlLettaTerminal.getBounds().width) / 2, (screenSize.height - shlLettaTerminal.getBounds().height) / 2);
		Combo productCombo = new Combo(shlLettaTerminal, SWT.READ_ONLY);

		line = new StringBuilder();
		comm = new SerialComm();
		
		switch (productCombo.getSelectionIndex()) 
		{
			case 0:	testerObj = new Monime(comm);
				break;
			
			// More cases...
	
			default: testerObj = new Monime(comm);
				break;
		}
		
		comm.setTester(testerObj);

		Combo portCombo = new Combo(shlLettaTerminal, SWT.READ_ONLY);
		Combo baudRateCombo = new Combo(shlLettaTerminal, SWT.READ_ONLY);
		Label lblPort = new Label(shlLettaTerminal, SWT.NONE);
		Button btnNext = new Button(shlLettaTerminal, SWT.NONE);
		btnNext.setEnabled(false);
		Button btnTest = new Button(shlLettaTerminal, SWT.NONE);
		btnTest.setEnabled(false);
		StyledText styledText = new StyledText(shlLettaTerminal, SWT.BORDER | SWT.READ_ONLY | SWT.V_SCROLL);
		StyledText textCommand = new StyledText(shlLettaTerminal, SWT.BORDER | SWT.SINGLE);

		Button btnClear = new Button(shlLettaTerminal, SWT.NONE);
		Label lblBaudRate = new Label(shlLettaTerminal, SWT.RIGHT);
		FormData fd_baudRateCombo = new FormData();
		fd_baudRateCombo.bottom = new FormAttachment(portCombo, -17);
		fd_baudRateCombo.right = new FormAttachment(productCombo, 0, SWT.RIGHT);
		fd_baudRateCombo.left = new FormAttachment(productCombo, 0, SWT.LEFT);
		FormData fd_lblPort = new FormData();
		fd_lblPort.right = new FormAttachment(100, -224);
		fd_lblPort.left = new FormAttachment(styledText, 22);
		fd_lblPort.top = new FormAttachment(0, 91);
		
		
		comm.setConsoleLogString(styledText);
		FormData fd_lblBaudRate = new FormData();
		fd_lblBaudRate.bottom = new FormAttachment(lblPort, -25);
		fd_lblBaudRate.top = new FormAttachment(baudRateCombo, 3, SWT.TOP);
		fd_lblBaudRate.left = new FormAttachment(styledText, 6);
		fd_lblBaudRate.right = new FormAttachment(100, -224);
		FormData fd_portCombo = new FormData();
		fd_portCombo.left = new FormAttachment(lblPort, 28);
		fd_portCombo.top = new FormAttachment(0, 88);
		fd_portCombo.right = new FormAttachment(100, -10);
		FormData fd_btnTest = new FormData();
		fd_btnTest.bottom = new FormAttachment(btnClear, -92);
		fd_btnTest.top = new FormAttachment(0, 501);
		fd_btnTest.left = new FormAttachment(styledText, 62);
		fd_btnTest.right = new FormAttachment(100, -43);
		FormData fd_btnNext = new FormData();
		fd_btnNext.bottom = new FormAttachment(btnTest, -38);
		fd_btnNext.left = new FormAttachment(styledText, 62);
		fd_btnNext.right = new FormAttachment(btnTest, 0, SWT.RIGHT);

		lblBaudRate.setBackground(SWTResourceManager.getColor(51, 51, 51));
		lblBaudRate.setText("BAUD RATES   :");
		lblBaudRate.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblBaudRate.setLayoutData(fd_lblBaudRate);
		
		lblPort.setBackground(SWTResourceManager.getColor(51, 51, 51));
		lblPort.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPort.setAlignment(SWT.RIGHT);
		lblPort.setText("PORTS   :");
		lblPort.setLayoutData(fd_lblPort);
	
		
		portCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		portCombo.setBackground(SWTResourceManager.getColor(51, 51, 51));
		portCombo.setTouchEnabled(true);
		
		/**
		 * Finds the available ports and shows to choose.
		 */
		portCombo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) 
			{
				String[] comPortNames = comm.getCommPortNames();
				portCombo.removeAll();
				
				if (comPortNames.length == 0) {
					portCombo.add("");
				}

				for (int i = 0; i < comPortNames.length; ++i)
					portCombo.add(comPortNames[i]);
			}
		});
		portCombo.setLayoutData(fd_portCombo);
		portCombo.setText("");
		
		/**
		 * Test All Button to test all commands in the list.
		 */
		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if (isPortOpen)
				{
					comm.setIsTest(true);
					testerObj.setNext(false);
					testerObj.test();
				}
				else
	            {
	                JOptionPane.showMessageDialog(null,"Port is not available!","",JOptionPane.ERROR_MESSAGE);
	            }
			}
		});
		btnTest.setText("TEST ALL COMMANDS");
		btnTest.setLayoutData(fd_btnTest);
		
		btnNext.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		/**
		 * Next Button to test next command.
		 */
		btnNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (isPortOpen)
	            {
					comm.setIsTest(true);
					testerObj.setNext(true);
					testerObj.test();
					testerObj.setNextIndex(testerObj.getNextIndex() + 1);
	            }
	            else
	            {
	                JOptionPane.showMessageDialog(null,"Port is not available!","",JOptionPane.ERROR_MESSAGE);
	            }
			}
		});
		btnNext.setText("TEST NEXT COMMAND");
		btnNext.setLayoutData(fd_btnNext);
		
		styledText.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		/**
		 * Scrolled down text is modified to show last line.
		 */
		styledText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				styledText.setTopIndex(styledText.getLineCount() - 1);				  
			}
		});
		styledText.setText("");
		
		FormData fd_styledText = new FormData();
		fd_styledText.top = new FormAttachment(0);
		fd_styledText.right = new FormAttachment(100, -335);
		fd_styledText.left = new FormAttachment(0);
		styledText.setLayoutData(fd_styledText);
		textCommand.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		textCommand.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnClear.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		
		/**
		 * Clears history of shell.
		 */
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				styledText.setText("");
			}
		});
		btnClear.setText("CLEAR SCREEN");
		FormData fd_btnClear = new FormData();
		fd_btnClear.bottom = new FormAttachment(textCommand, 0, SWT.BOTTOM);
		fd_btnClear.left = new FormAttachment(0, 767);
		fd_btnClear.right = new FormAttachment(100, -191);
		btnClear.setLayoutData(fd_btnClear);
		fd_styledText.bottom = new FormAttachment(100, -57);
		
		/**
		 * Using the command line to send commands.
		 */
		textCommand.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == 13) {					
					if (!isPortOpen) {
						styledText.append("Error : There is no connection to ports!\r\n");
					}
					else {					
						comm.setIsTest(true);
						comm.writeToPortString(line.toString()+ "\n");
					}
					line = new StringBuilder("");
					textCommand.setText("");
				}
				if (e.character == 8 && line.length() != 0) {
					line.deleteCharAt(line.length()-1);
				}
				else
					line.append(e.character);
			}
		});
		FormData fd_textCommand = new FormData();
		fd_textCommand.right = new FormAttachment(btnClear, -24);
		fd_textCommand.left = new FormAttachment(0, 10);
		fd_textCommand.top = new FormAttachment(styledText, 6);
		fd_textCommand.bottom = new FormAttachment(100, -22);
		textCommand.setLayoutData(fd_textCommand);
		baudRateCombo.setTouchEnabled(true);
		baudRateCombo.setItems(new String[] {"300", "600", "1200", "2400", "4800", "9600", "14400", "19200", "38400", "57600", "115200", "128000", "256000"});
		baudRateCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		baudRateCombo.setBackground(SWTResourceManager.getColor(51, 51, 51));
		baudRateCombo.setLayoutData(fd_baudRateCombo);
		baudRateCombo.select(10);
		baudRateCombo.setText("");
		Button btnStart = new Button(shlLettaTerminal, SWT.NONE);
		fd_btnNext.top = new FormAttachment(0, 385);

		Button btnStop = new Button(shlLettaTerminal, SWT.NONE);
		btnStop.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comm.Close();
				isPortOpen = false;
				btnStart.setEnabled(true);
				btnStop.setEnabled(false);
				btnTest.setEnabled(false);
				btnNext.setEnabled(false);
				portCombo.setEnabled(true);
				baudRateCombo.setEnabled(true);
				productCombo.setEnabled(true);
			}
		});
		btnStop.setText("STOP");
		btnStop.setEnabled(false);
		FormData fd_btnStop = new FormData();
		fd_btnStop.bottom = new FormAttachment(btnNext, -68);
		fd_btnStop.left = new FormAttachment(btnStart, 20);
		fd_btnStop.right = new FormAttachment(btnNext, 0, SWT.RIGHT);
		btnStop.setLayoutData(fd_btnStop);
		
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				if (portCombo.getSelectionIndex() < 0 || portCombo.getText().equals(""))
	                JOptionPane.showMessageDialog(null,"There is no connection to ports!","Error",JOptionPane.ERROR_MESSAGE);
	            else {
	            	int br = Integer.parseInt(baudRateCombo.getItem(baudRateCombo.getSelectionIndex()));
	                if (!comm.Open(portCombo.getSelectionIndex(), br))
	                {
	                    JOptionPane.showMessageDialog(null,"Can not connect to " + portCombo.getItem(portCombo.getSelectionIndex()) + ". port!","Error",JOptionPane.ERROR_MESSAGE);
	                }
	                else
	                {
	                	isPortOpen = true;
	                    btnStart.setEnabled(false);
	                    btnStop.setEnabled(true);
	                    btnTest.setEnabled(true);
	    				btnNext.setEnabled(true);
						textCommand.setEnabled(true);
	                    portCombo.setEnabled(false);
	    				baudRateCombo.setEnabled(false);
	    				productCombo.setEnabled(false);
	                }
	            }
			}
		});
		btnStart.setText("START");
		FormData fd_btnStart = new FormData();
		fd_btnStart.bottom = new FormAttachment(btnNext, -68);
		fd_btnStart.left = new FormAttachment(btnNext, 0, SWT.LEFT);
		fd_btnStart.right = new FormAttachment(100, -168);
		btnStart.setLayoutData(fd_btnStart);
		
		Label lblProduct = new Label(shlLettaTerminal, SWT.NONE);
		lblProduct.setText("PRODUCTS  :");
		lblProduct.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblProduct.setBackground(SWTResourceManager.getColor(51, 51, 51));
		lblProduct.setAlignment(SWT.RIGHT);
		FormData fd_lblProduct = new FormData();
		fd_lblProduct.top = new FormAttachment(productCombo, 3, SWT.TOP);
		fd_lblProduct.right = new FormAttachment(productCombo, -14);
		fd_lblProduct.left = new FormAttachment(lblPort, 0, SWT.LEFT);
		lblProduct.setLayoutData(fd_lblProduct);
		
		productCombo.setItems(new String[] {"Monime"});
		fd_btnStop.top = new FormAttachment(productCombo, 61);
		fd_btnStart.top = new FormAttachment(productCombo, 61);
		productCombo.setTouchEnabled(true);
		productCombo.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		productCombo.setBackground(SWTResourceManager.getColor(51, 51, 51));
		FormData fd_productCombo = new FormData();
		fd_productCombo.top = new FormAttachment(portCombo, 16);
		fd_productCombo.left = new FormAttachment(0, 878);
		fd_productCombo.right = new FormAttachment(100, -10);
		productCombo.setLayoutData(fd_productCombo);
		productCombo.select(0);
		productCombo.setText("");
	}
}
