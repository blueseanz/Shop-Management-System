package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Locale;

@SuppressWarnings("serial") 
public class ClerkMgnView extends JFrame {

	private JPanel contentPane;
	private JTextField txtClerkPassword;
	private JTextField txtClerkName;
	private JTextField txtClerkID;
	private JLabel lblWelcome;
	private JLabel lblClerkMgn;
	private JLabel msgLabel; 
	private JButton btnSubmit;
	private JButton btnCancel;

	public ClerkMgnView() {
		setTitle("Shopping Management System");
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 470;
	    int windowHeight = 450;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font myFont = new Font("Arial", Font.PLAIN, 15);
		msgLabel= new JLabel();
		msgLabel.setFont(myFont);
		JOptionPane.setDefaultLocale(Locale.ENGLISH);
		
		JLabel lblClerkID = new JLabel("Clerk ID");
		lblClerkID.setFont(myFont);
		lblClerkID.setBounds(57, 98, 93, 35);
		contentPane.add(lblClerkID);
		
		JLabel lblClerkName = new JLabel("Clerk Name");
		lblClerkName.setFont(myFont);
		lblClerkName.setBounds(57, 163, 93, 35);
		contentPane.add(lblClerkName);
		
		JLabel lblClerkPassword = new JLabel("Password");
		lblClerkPassword.setFont(myFont);
		lblClerkPassword.setBounds(57, 229, 72, 35);
		contentPane.add(lblClerkPassword);
		
		txtClerkPassword = new JTextField();
		txtClerkPassword.setFont(myFont);
		txtClerkPassword.setColumns(10);
		txtClerkPassword.setBounds(164, 229, 193, 35);
		contentPane.add(txtClerkPassword);
		
		txtClerkName = new JTextField();
		txtClerkName.setFont(myFont);
		txtClerkName.setColumns(10);
		txtClerkName.setBounds(164, 163, 193, 35);
		contentPane.add(txtClerkName);
		
		txtClerkID = new JTextField();
		txtClerkID.setEditable(false);
		txtClerkID.setFont(myFont);
		txtClerkID.setColumns(10);
		txtClerkID.setBounds(164, 98, 193, 35);
		contentPane.add(txtClerkID);
		
		lblWelcome = new JLabel("Welcome to Shopping Management System");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(myFont);
		lblWelcome.setBounds(14, 13, 422, 38);
		contentPane.add(lblWelcome);
		
		lblClerkMgn = new JLabel("Clerk Management");
		lblClerkMgn.setHorizontalAlignment(SwingConstants.CENTER);
		lblClerkMgn.setFont(myFont);
		lblClerkMgn.setBounds(142, 53, 145, 38);
		contentPane.add(lblClerkMgn);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(myFont);
		btnSubmit.setBounds(57, 307, 137, 35);
		contentPane.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(myFont);
		btnCancel.setBounds(219, 307, 137, 35);
		contentPane.add(btnCancel);
	}
	
	public void setMgnTitle(String theMgnTitle)
	{
		lblClerkMgn.setText(theMgnTitle);
	}
	public String getMgnTitle()
	{
		return lblClerkMgn.getText();
	}
	public String getClerkID()
	{
		return txtClerkID.getText();
	}
	public void setClerkID(String sClerkID)
	{
		txtClerkID.setText(sClerkID);
	}
	public String getClerkName()
	{
		return txtClerkName.getText();
	}	
	public void setClerkName(String sClerkName)
	{
		txtClerkName.setText(sClerkName);
	}public String getClerkPassword()
	{
		return txtClerkPassword.getText();
	}	
	public void setClerkPassword(String sClerkPassword)
	{
		txtClerkPassword.setText(sClerkPassword);
	}
	public void setSubmitButtonTitle(String title)
	{
		btnSubmit.setText(title);
	}
	public void displayClerkNameRequired()
	{
		msgLabel.setText("Clerk Name is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Clerk Admin",JOptionPane.ERROR_MESSAGE);
	}
	public void displayClerkPasswordRequired()
	{
		msgLabel.setText("Clerk Password is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Clerk Admin",JOptionPane.ERROR_MESSAGE);
	}
	public void setClerkNamePasswordNotEditable()
	{
		txtClerkName.setEditable(false);
		txtClerkPassword.setEditable(false);
	}
	public void addClerkMgnViewListener(ActionListener listenForButton)
	{
		btnSubmit.addActionListener(listenForButton);
		btnCancel.addActionListener(listenForButton);
	}
}
