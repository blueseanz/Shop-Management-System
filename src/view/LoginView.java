package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Locale;

@SuppressWarnings("serial") 
public class LoginView extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JTextField txtUserName;
	private JButton btnSubmit, btnQuit;
	private JLabel msgLabel;
	private JLabel lblSubTitile;
	public LoginView() {	
		
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 470;
	    int windowHeight = 450;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,windowHeight);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Shopping Management System");
	    
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font myFont = new Font("Arial", Font.PLAIN, 15);
		msgLabel= new JLabel();
		msgLabel.setFont(myFont);
		JOptionPane.setDefaultLocale(Locale.ENGLISH);
		
		JLabel lblWelcome = new JLabel("Welcome to Shopping Management System");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(myFont);
		lblWelcome.setBounds(14, 13, 422, 38);
		contentPane.add(lblWelcome);
		
		lblSubTitile = new JLabel("Account Login");
		lblSubTitile.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitile.setFont(myFont);
		lblSubTitile.setBounds(109, 63, 250, 38);
		contentPane.add(lblSubTitile);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(myFont);
		lblUserName.setBounds(45, 144, 122, 35);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(myFont);
		lblPassword.setBounds(45, 198, 122, 35);
		contentPane.add(lblPassword);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(myFont);
		btnSubmit.setBounds(45, 281, 150, 35);
		contentPane.add(btnSubmit);
		
		txtPassword = new JPasswordField();
		txtPassword.setText("");
		txtPassword.setFont(myFont);
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(220, 198, 192, 35);
		contentPane.add(txtPassword);
		
		txtUserName = new JTextField();
		txtUserName.setText("");
		txtUserName.setFont(myFont);
		txtUserName.setColumns(10);
		txtUserName.setBounds(220, 144, 192, 35);
		contentPane.add(txtUserName);
		
		btnQuit = new JButton("Quit");
		btnQuit.setFont(myFont);
		btnQuit.setBounds(262, 281, 150, 35);
		contentPane.add(btnQuit); 
	}
	
	public String getUserName()
	{
		return txtUserName.getText();
	}
	public String getPassword()
	{
		return new String(txtPassword.getPassword());
	}
	public void displayLoginSuccess()
	{
		msgLabel.setText("Your login is successful!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Login Status",JOptionPane.INFORMATION_MESSAGE);
	}
	public void displayLoginUnSuccess()
	{
		msgLabel.setText("Incorrect User Name or Password!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Login Status",JOptionPane.ERROR_MESSAGE);
		txtUserName.setText("");
		txtPassword.setText("");
	}	
	public void displayLoginUnSuccessForThreeTimes()
	{
		msgLabel.setText("Your login is unsuccessful!\nYou have tried for 3 times!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Login Status",JOptionPane.ERROR_MESSAGE);
	}	
	public void displayLoginNameRequired()
	{
		msgLabel.setText("Account Name is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Login Status",JOptionPane.ERROR_MESSAGE);
	}
	public void displayLoginPasswordRequired()
	{
		msgLabel.setText("Account Password is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Login Status",JOptionPane.ERROR_MESSAGE);
	}
	public void addLoginViewListener(ActionListener listenForButton)
	{
		btnSubmit.addActionListener(listenForButton);
		btnQuit.addActionListener(listenForButton);
	}
	public void setSubTitle(String subTitle)
	{
		lblSubTitile.setText(subTitle);
	}
	public void setSubmitButtonTitle(String title)
	{
		btnSubmit.setText(title);
	}
}
