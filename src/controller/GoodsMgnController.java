package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.DBAccess;
import view.GoodsMgnView;
import view.MainView;

public final class GoodsMgnController {
	private GoodsMgnView theGoodsMgnView;
	private MainView theMainView;
	private DBAccess theDBAccess;

	public GoodsMgnController(GoodsMgnView theGoodsMgnView, MainView theMainView,DBAccess theDBAccess) 
	{
		this.theDBAccess = theDBAccess;
		this.theGoodsMgnView = theGoodsMgnView;
		this.theGoodsMgnView.addGoodsMgnViewListener(new GoodsMgnViewListener());
		this.theMainView = theMainView;
	}
	public void PleaseWait()
	{
		System.out.print("Please wait to start the goods management...\n");
	}
	class GoodsMgnViewListener implements ActionListener{
		public void actionPerformed(ActionEvent e) 
        {
        	if ((e.getActionCommand().contains("Add")) || (e.getActionCommand().contains("Update")))
        	{
        		if (theGoodsMgnView.getGoodsName().length() == 0)
        		{
        			theGoodsMgnView.displayGoodsNameRequired();
        		} else
        		{
        			theDBAccess.insDelUpdGoods(theGoodsMgnView,theMainView.getDML());
        			theDBAccess.loadTable(theMainView);
        			theGoodsMgnView.dispose();
        		}
        	} else if (e.getActionCommand().contains("Delete"))  
        	{
        		theDBAccess.insDelUpdGoods(theGoodsMgnView,theMainView.getDML());
        		theDBAccess.loadTable(theMainView);
         		theGoodsMgnView.dispose();
        	} else if(e.getActionCommand().contains("Cancel")) 
        	
        	{
        		theGoodsMgnView.dispose();
        	}
        }
	}
	
}
