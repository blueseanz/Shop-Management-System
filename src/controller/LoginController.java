package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.MainView;
import model.DBAccess;

public final class LoginController {
	private LoginView theLoginView;
	private String theUserId = "";
	private DBAccess theDBAccess;
	
	//Login successful, enter to main view
	public void EnterMainController()
	{
		MainView theMainView = new MainView();
		theMainView.getContentTable("GOODS");
		MainController theMainController = new MainController(theMainView,theDBAccess);
		theMainController.PleaseWait();
		theMainView.setVisible(true);	
	}
	       
	public LoginController(LoginView theLoginView,DBAccess thedbAccess) 
	{
		this.theLoginView = theLoginView;
		this.theDBAccess = thedbAccess;
		this.theLoginView.addLoginViewListener(new LoginViewListener());
	}	
	
	public void PleaseWait()
	{
		System.out.print("Please wait to login...\n");
	}
	
	//public void 
	class LoginViewListener implements ActionListener{

		int loginTimes = 3;

		public void actionPerformed(ActionEvent e) 
        {
        	if(e.getActionCommand().contains("Submit")) 
        	{
        		if (loginTimes > 0)
        		{
    				theUserId = theDBAccess.getUserIDByUserNamePassword(theLoginView);

    				if (theUserId != "")
    				{ 
    					theLoginView.displayLoginSuccess();
    					theLoginView.dispose();
    					EnterMainController();
    				} else
    				{
    					loginTimes--;
    					
    					if (loginTimes > 0)
    					{
    						theLoginView.displayLoginUnSuccess();
    					} else if (loginTimes == 0)
    					{
    						theLoginView.displayLoginUnSuccessForThreeTimes();
    						theLoginView.dispose();
    					}
    				}
        			
        		} else
        		{
        			theLoginView.dispose();
        		}
        	} else if (e.getActionCommand().contains("Create")) 
        	{
        		if (theLoginView.getUserName().length() == 0)
        		{
        			theLoginView.displayLoginNameRequired();
        		} else if (theLoginView.getPassword().length() == 0)
        		{
        			theLoginView.displayLoginPasswordRequired();
        		} else
        		{
        			theDBAccess.createAccount(theLoginView);
            		theLoginView.dispose();
            		EnterMainController();       			
        		}
        	} else if (e.getActionCommand().contains("Quit")) 
        	{
        		theLoginView.dispose();
        	}
        }
    }
}     




