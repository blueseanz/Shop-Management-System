package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("serial")
public class GoodsMgnView extends JFrame {

	private JPanel contentPane;
	private JTextField txtGoodsID;
	private JTextField txtGoodsName;
	private JButton btnSubmit;
	private JButton btnCancel;
	private JLabel lblGoodsStockLevel;
	private JLabel msgLabel; 
	private JFormattedTextField ftxtGoodsUnitPrice;
	private JFormattedTextField ftxtGoodsStockLevel;
	private JLabel lblGoodsMgn;
    private NumberFormat fmtUnitPrice;
	private NumberFormat fmtStockLevel;


	public GoodsMgnView() {
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

		JLabel lblWelcome = new JLabel("Welcome to Shopping Management System");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(myFont);
		lblWelcome.setBounds(14, 7, 422, 38);
		contentPane.add(lblWelcome);
		
		lblGoodsMgn = new JLabel("Goods Management");
		lblGoodsMgn.setHorizontalAlignment(SwingConstants.CENTER);
		lblGoodsMgn.setFont(myFont);
		lblGoodsMgn.setBounds(142, 43, 145, 38);
		contentPane.add(lblGoodsMgn);
		
		JLabel lblGoodsID = new JLabel("Goods ID");
		lblGoodsID.setFont(myFont);
		lblGoodsID.setBounds(45, 89, 93, 35);
		contentPane.add(lblGoodsID);
		
		txtGoodsID = new JTextField();
		txtGoodsID.setBackground(new Color(255, 255, 255));
		txtGoodsID.setFont(myFont);
		txtGoodsID.setEditable(false);
		txtGoodsID.setColumns(10);
		txtGoodsID.setBounds(164, 89, 193, 35);
		contentPane.add(txtGoodsID);
		
		txtGoodsName = new JTextField();
		txtGoodsName.setFont(myFont);
		txtGoodsName.setColumns(10);
		txtGoodsName.setBounds(164, 151, 193, 35);
		contentPane.add(txtGoodsName);
		
		JLabel lblGoodsName = new JLabel("Goods Name");
		lblGoodsName.setFont(myFont);
		lblGoodsName.setBounds(45, 151, 93, 35);
		contentPane.add(lblGoodsName);
		
		JLabel lblGoodsUnitPrice = new JLabel("Unit Price");
		lblGoodsUnitPrice.setFont(myFont);
		lblGoodsUnitPrice.setBounds(45, 212, 72, 35);
		contentPane.add(lblGoodsUnitPrice);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(myFont);
		btnSubmit.setBounds(45, 341, 137, 35);
		contentPane.add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(myFont);
		btnCancel.setBounds(220, 341, 137, 35);
		contentPane.add(btnCancel);
		
		lblGoodsStockLevel = new JLabel("Stock Level");
		lblGoodsStockLevel.setFont(myFont);
		lblGoodsStockLevel.setBounds(45, 273, 84, 35);
		contentPane.add(lblGoodsStockLevel);
		
		fmtUnitPrice = NumberFormat.getNumberInstance();
		fmtUnitPrice.setMaximumFractionDigits(2);
		fmtUnitPrice.setMinimumFractionDigits(2);
		
		fmtStockLevel = NumberFormat.getNumberInstance();
		fmtStockLevel.setMaximumFractionDigits(0);
		fmtStockLevel.setMinimumFractionDigits(0);		
		
		ftxtGoodsUnitPrice = new JFormattedTextField(fmtUnitPrice);
		ftxtGoodsUnitPrice.setFont(myFont);
		ftxtGoodsUnitPrice.setBounds(164, 217, 193, 35);
		contentPane.add(ftxtGoodsUnitPrice);
		
		ftxtGoodsStockLevel = new JFormattedTextField(fmtStockLevel);
		ftxtGoodsStockLevel.setFont(myFont);
		ftxtGoodsStockLevel.setBounds(164, 278, 193, 35);
		contentPane.add(ftxtGoodsStockLevel);
	}
	public String getGoodsID()
	{
		return txtGoodsID.getText();
	}
	public void setGoodsID(String sGoodsID)
	{
		txtGoodsID.setText(sGoodsID);
	}
	public String getGoodsName()
	{
		return txtGoodsName.getText();
	}
	public void setGoodsName(String sGoodsName)
	{
		txtGoodsName.setText(sGoodsName);
	}
	public String getGoodsUnitPrice()
	{
		return Float.toString(((Number)ftxtGoodsUnitPrice.getValue()).floatValue());
	}
	public void setGoodsUnitPrice(String sGoodsUnitPrice)
	{
		if (sGoodsUnitPrice == "") 
		{
			ftxtGoodsUnitPrice.setText("");
		} else
		{
			ftxtGoodsUnitPrice.setValue(new Float(Float.parseFloat(sGoodsUnitPrice)));
		}
	}
	public String getGoodsStockLevel()
	{
		return Integer.toString(((Number)ftxtGoodsStockLevel.getValue()).intValue());
	}
	public void setGoodsStockLevel(String sGoodsStockLevel)
	{
		if (sGoodsStockLevel == "")
		{
			ftxtGoodsStockLevel.setText("");
		}else
		{
			ftxtGoodsStockLevel.setValue(new Integer(Integer.parseInt(sGoodsStockLevel)));
		}
	}
	public void setSubmitButtonTitle(String title)
	{
		btnSubmit.setText(title);
	}
	public void setMgnTitle(String theMgnTitle)
	{
		lblGoodsMgn.setText(theMgnTitle);
	}

	public void addGoodsMgnViewListener(ActionListener listenForButton)
	{
		btnSubmit.addActionListener(listenForButton);
		btnCancel.addActionListener(listenForButton);
	}
	public void displayGoodsNameRequired()
	{
		msgLabel.setText("Item Name is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Items Admin",JOptionPane.ERROR_MESSAGE);
	}
	public void displayGoodsUnitPriceRequired()
	{
		msgLabel.setText("Item Unit Price is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Items Admin",JOptionPane.ERROR_MESSAGE);
	}
	public void displayGoodsStockLevelRequired()
	{
		msgLabel.setText("Item Stock Level is required!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Items Admin",JOptionPane.ERROR_MESSAGE);
	}
	public void setGoodsNameUnitPriceStockLevelNotEditable()
	{
		txtGoodsName.setEditable(false);
		ftxtGoodsUnitPrice.setEditable(false);
		ftxtGoodsStockLevel.setEditable(false);
	}

}
