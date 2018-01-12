package SMSMain;

import controller.LoginController;
import view.LoginView;
import model.DBAccess;

public final class SMSMainEntrance {
	public static void main(String[] args) 
	{
		DBAccess theDBAccess = new DBAccess(); 
		LoginView theLoginView = new LoginView();
		boolean newAccount = (theDBAccess.getTableRowCount("SALESMAN") == 0);
		
		if ( newAccount) 
		{
			theLoginView.setSubTitle("No account exists, create a new one!");
			theLoginView.setSubmitButtonTitle("Create");
		} 
		LoginController theLoginController = new LoginController(theLoginView,theDBAccess);
		theLoginController.PleaseWait();
		theLoginView.setVisible(true);
	}
}
