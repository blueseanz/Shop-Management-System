package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DBAccess;
import view.ClerkMgnView;
import view.MainView;

public final class ClerkMgnController {
	private ClerkMgnView theClerkMgnView;
	private MainView theMainView;
	private DBAccess theDBAccess;
	
	public ClerkMgnController(ClerkMgnView theClerkMgnView, MainView theMainView,DBAccess theDBAccess) 
	{
		this.theDBAccess = theDBAccess;
		this.theClerkMgnView = theClerkMgnView;
		this.theClerkMgnView.addClerkMgnViewListener(new ClerkMgnViewListener());
		this.theMainView = theMainView;
	}
	public void PleaseWait()
	{
		System.out.print("Please wait to start the clerk management...\n");
	}
	class ClerkMgnViewListener implements ActionListener{
		public void actionPerformed(ActionEvent e) 
        {
        	if ((e.getActionCommand().contains("Add")) || (e.getActionCommand().contains("Update")))
        	{
        		if (theClerkMgnView.getClerkName().length() == 0)
        		{
        			theClerkMgnView.displayClerkNameRequired();
        		}else if (theClerkMgnView.getClerkPassword().length() == 0)
        		{
        			theClerkMgnView.displayClerkPasswordRequired();
        		}else
        		{
        			theDBAccess.insDelUpdSalesMan(theClerkMgnView,theMainView.getDML());
        			theDBAccess.loadTable(theMainView);
        			theClerkMgnView.dispose();
        		}
        	} else if (e.getActionCommand().contains("Delete"))  
        	{
        		theDBAccess.insDelUpdSalesMan(theClerkMgnView,theMainView.getDML());
        		theDBAccess.loadTable(theMainView);
        		theClerkMgnView.dispose();
        		
        	} else if(e.getActionCommand().contains("Cancel"))         	
        	{
        		theClerkMgnView.dispose();
        	}
        }
	}
}
