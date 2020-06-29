package com.autotest;

import java.util.LinkedList;

abstract class Test 
{
	protected SerialComm comm;
	protected LinkedList<String> testCommands;
	protected boolean next = false;
	protected boolean isScannable = false; 
	protected int nextIndex = 0;
    protected String lettaSerialNo;
    protected String companySerialNo;
    protected String csvOutputFileName;
    protected final String configSetLetta = "configSet 1 ";
    protected final String configSetCompany = "configSet 2 ";
    protected final String configGetLetta = "configGet 1 ";
    protected final String configGetCompany = "configGet 2 ";

	Test(SerialComm comm) 
	{
		this.comm = comm;
		this.testCommands = new LinkedList<>();
	}
		
	boolean getScannable()
	{
		return isScannable;
	}
	
	void setNextIndex(int nextIndex)
	{
		this.nextIndex = nextIndex >= testCommands.size() || nextIndex < 0 ? 0 : nextIndex;
	}
	
	void setNext(boolean next)
	{
		this.next = next;
	}
	
	boolean getNext()
	{
		return next;
	}
	
	int getNextIndex()
	{
		return nextIndex;
	}
	
	abstract void test();
	
	abstract void configure(String readData);
	
	abstract void showResults();
}
