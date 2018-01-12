package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import model.DBAccess;
import view.MainView;
import view.ClerkMgnView;
import view.GoodsMgnView;
import view.LoginView;
import tools.MiscFunction;

public final class MainController {
	private MainView theMainView;
	private DBAccess theDBAccess;

	public MainController(MainView theMainView,DBAccess theDBAccess) 
	{
		this.theDBAccess = theDBAccess;
		theDBAccess.createSalesCartTable();
		theDBAccess.loadTable(theMainView);

		this.theMainView = theMainView;
		this.theMainView.addMainViewListener(new MainViewListener());
		this.theMainView.addMainViewItemListener(new MainViewItemChangeListener());
		this.theMainView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				theDBAccess.dropSalesCartTable();
			}
		});
	}	
	
	public void PleaseWait()
	{
		System.out.print("Please wait to start the main application...\n");
	}
	class MainViewItemChangeListener implements ItemListener{
	    @Override
	    public void itemStateChanged(ItemEvent event) {
	       if (event.getStateChange() == ItemEvent.SELECTED) {
	    	   theDBAccess.loadTable(theMainView);
	       }
	    }       
	}
	class MainViewListener implements ActionListener{
		public void actionPerformed(ActionEvent e) 
        {
			if(e.getActionCommand().contains("Search Clerks")) 
        	{	
				theDBAccess.queryTable(theMainView,"SALESMAN");
				if (theMainView.getContentTable("SALESMAN").getRowCount() > 0)
				{
					theMainView.getContentTable("SALESMAN").setRowSelectionInterval(0, 0);
				}
				theMainView.getClerkReadyToDisplay(true);
        	} else if (e.getActionCommand().contains("Add a Clerk")) 
        	{
    			ClerkMgnView theClerkMgnView = new ClerkMgnView();
        		ClerkMgnController theClerkMgnController = new ClerkMgnController(theClerkMgnView,theMainView,theDBAccess);
        		theClerkMgnController.PleaseWait();
    			
        		theClerkMgnView.setMgnTitle("Add a Clerk");
        		theClerkMgnView.setClerkID(MiscFunction.getIDByCurrentTime());
        		theClerkMgnView.setClerkName("");
        		theClerkMgnView.setClerkPassword("");
        		theClerkMgnView.setSubmitButtonTitle("Add");
    			theClerkMgnView.setVisible(true);
    			theMainView.setDML("add");
        	}else if (e.getActionCommand().contains("Delete a Clerk")) 
        	{
    			ClerkMgnView theClerkMgnView = new ClerkMgnView();
        		ClerkMgnController theClerkMgnController = new ClerkMgnController(theClerkMgnView,theMainView,theDBAccess);
        		theClerkMgnController.PleaseWait();
    			
        		theClerkMgnView.setMgnTitle("Delete a Clerk");
           		theClerkMgnView.setClerkID(theMainView.getClerkID());
        		theClerkMgnView.setClerkName(theMainView.getClerkName());
        		theClerkMgnView.setClerkPassword(theMainView.getClerkPassword());
        		theClerkMgnView.setSubmitButtonTitle("Delete");
        		theClerkMgnView.setClerkNamePasswordNotEditable();
    			theClerkMgnView.setVisible(true);
    			theMainView.setDML("delete");
        	}else if (e.getActionCommand().contains("Update a Clerk")) 
        	{
    			ClerkMgnView theClerkMgnView = new ClerkMgnView();
        		ClerkMgnController theClerkMgnController = new ClerkMgnController(theClerkMgnView,theMainView,theDBAccess);
        		theClerkMgnController.PleaseWait();
    			
        		theClerkMgnView.setMgnTitle("Update a Clerk");
           		theClerkMgnView.setClerkID(theMainView.getClerkID());
        		theClerkMgnView.setClerkName(theMainView.getClerkName());
        		theClerkMgnView.setClerkPassword(theMainView.getClerkPassword());
        		theClerkMgnView.setSubmitButtonTitle("Update");
        		theClerkMgnView.setVisible(true);
    			theMainView.setDML("update");
        	}else if (e.getActionCommand().contains("Search Items")) 
        	{
				theDBAccess.queryTable(theMainView,"GOODS");
				if (theMainView.getContentTable("GOODS").getRowCount() > 0)
				{
					theMainView.getContentTable("GOODS").setRowSelectionInterval(0, 0);
				}
				theMainView.getGoodsReadyToDisplay(true);
        	}else if (e.getActionCommand().contains("Add an Item")) 
        	{
        		GoodsMgnView theGoodsMgnView = new GoodsMgnView();
        		GoodsMgnController theGoodsMgnController = new GoodsMgnController(theGoodsMgnView,theMainView,theDBAccess);
        		theGoodsMgnController.PleaseWait();
    			
        		theGoodsMgnView.setMgnTitle("Add an Item");
        		theGoodsMgnView.setGoodsID(MiscFunction.getIDByCurrentTime());
        		theGoodsMgnView.setGoodsName("");
        		theGoodsMgnView.setGoodsUnitPrice("");
        		theGoodsMgnView.setGoodsStockLevel("");        		
        		theGoodsMgnView.setSubmitButtonTitle("Add");
        		theGoodsMgnView.setVisible(true);
    			theMainView.setDML("add");
        	}else if (e.getActionCommand().contains("Delete an Item")) 
        	{
        		GoodsMgnView theGoodsMgnView = new GoodsMgnView();
        		GoodsMgnController theGoodsMgnController = new GoodsMgnController(theGoodsMgnView,theMainView,theDBAccess);
        		theGoodsMgnController.PleaseWait();
    			
        		theGoodsMgnView.setMgnTitle("Delete an Item");
        		theGoodsMgnView.setGoodsID(theMainView.getGoodsID());
        		theGoodsMgnView.setGoodsName(theMainView.getGoodsName());
        		theGoodsMgnView.setGoodsUnitPrice(theMainView.getGoodsUnitPrice());
        		theGoodsMgnView.setGoodsStockLevel(theMainView.getGoodsStockLevel());
        		theGoodsMgnView.setSubmitButtonTitle("Delete");
        		theGoodsMgnView.setGoodsNameUnitPriceStockLevelNotEditable();
        		theGoodsMgnView.setVisible(true);
    			theMainView.setDML("delete");
        	}else if (e.getActionCommand().contains("Update an Item")) 
        	{
        		GoodsMgnView theGoodsMgnView = new GoodsMgnView();
        		GoodsMgnController theGoodsMgnController = new GoodsMgnController(theGoodsMgnView,theMainView,theDBAccess);
        		theGoodsMgnController.PleaseWait();
    			
        		theGoodsMgnView.setMgnTitle("Update an Clerk");
        		theGoodsMgnView.setGoodsID(theMainView.getGoodsID());
        		theGoodsMgnView.setGoodsName(theMainView.getGoodsName());
        		theGoodsMgnView.setGoodsUnitPrice(theMainView.getGoodsUnitPrice());
        		theGoodsMgnView.setGoodsStockLevel(theMainView.getGoodsStockLevel());
        		theGoodsMgnView.setSubmitButtonTitle("Update");
        		theGoodsMgnView.setVisible(true);
    			theMainView.setDML("update");
        	}else if (e.getActionCommand().contains("Checkout")) 
        	{
        		theMainView.getTabbedPane().setSelectedIndex(1);
        	}else if (e.getActionCommand().contains("Add To Cart"))
        	{
        		int theTotalCart = 0;
        	
        		int theStock = Integer.parseInt(theMainView.getGoodsStockLevelBrowse());
        		int theAddToCart = Integer.parseInt(theMainView.getNumberOfItemsToCart());
        		int theCart = theDBAccess.getCartStockByBrowseID(theMainView);
        		
        		theTotalCart = theAddToCart + theCart;
        		        		
        		if(theTotalCart > theStock )
        		{
        			theMainView.OutOfStock();
        		}else
        		{
        			if (theCart == 0)
        			{
        				theDBAccess.insDelUpdGoodsBrowse(theMainView,"add",0);
        			}else
        			{
        				theDBAccess.insDelUpdGoodsBrowse(theMainView,"update",theTotalCart);
        			}        			
        			theDBAccess.loadTable(theMainView);
        		}        		
        	}else if (e.getActionCommand().contains("Logout"))
        	{
        		theMainView.dispose();
        		theDBAccess.dropSalesCartTable();
        		LoginView theLoginView = new LoginView();
        		LoginController theLoginController = new LoginController(theLoginView,theDBAccess);
        		theLoginController.PleaseWait();
        		theLoginView.setVisible(true);
        	}else if (e.getActionCommand().contains("Exit System"))
        	{
        		theMainView.dispose();
        		theDBAccess.dropSalesCartTable();
        	}else if (e.getActionCommand().contains("Change"))
        	{
        		int theStock = theDBAccess.getBrowseStockByCartID(theMainView);     		
        		
        		int theCart = Integer.parseInt(theMainView.getGoodsStockLevelCart());
        		int theChange = Integer.parseInt(theMainView.getNumberOfItemsToChange());
        		int theUpdateCart = Math.max(theCart,theChange);
        		
           		if(theUpdateCart > theStock )
        		{
        			theMainView.OutOfStock();
        		}else
        		{
        			theDBAccess.insDelUpdGoodsCart(theMainView,"update",theChange);
        			theDBAccess.loadTable(theMainView);
        		}        		
        	}else if (e.getActionCommand().contains("Add"))            	
        	{
        		int theStock = theDBAccess.getBrowseStockByCartID(theMainView);     		
        		
        		int theCart = Integer.parseInt(theMainView.getGoodsStockLevelCart());
        		int theChange = Integer.parseInt(theMainView.getNumberOfItemsToChange());
        		int theUpdateCart = theCart + theChange;
        		
           		if(theUpdateCart > theStock )
        		{
        			theMainView.OutOfStock();
        		}else
        		{
        			theDBAccess.insDelUpdGoodsCart(theMainView,"update",theUpdateCart);
        			theDBAccess.loadTable(theMainView);
        		}   		
        	}else if (e.getActionCommand().contains("Delete"))
        	{
       			theDBAccess.insDelUpdGoodsCart(theMainView,"delete",0);
    			theDBAccess.loadTable(theMainView);
        	}else if (e.getActionCommand().contains("Payment"))
        	{
        		if (theMainView.PaymentConfirm() == JOptionPane.YES_OPTION )
        		{
        			theDBAccess.doPayment();
        			theDBAccess.loadTable(theMainView);
        		}
        	}else if (e.getActionCommand().contains("Sales Page"))        		
        	{
        		theMainView.getTabbedPane().setSelectedIndex(0);
        	}else if (e.getActionCommand().contains("Search")) 
        	{
        		theDBAccess.dailySales(theMainView);
        	}
        }
		
	}
}
