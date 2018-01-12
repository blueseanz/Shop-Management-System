package view;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

@SuppressWarnings("serial") 
public class MainView extends JFrame {

	private JTabbedPane tabbedPane;
	private JPanel contentPane;
	
	private JTextField txtSearchClerk;
	private JTextField txtSearchGoods;
	
	private JComboBox<String> cbbSearchClerk;
	private JComboBox<String> cbbSearchGoods;
	private JComboBox<String> cbbSelectItemNumberToCart;
	private JComboBox<String> cbbSelectItemsNumberToChange;
	private JComboBox<String> cbbSalesByCategory;
	private JComboBox<String> cbbNameUnderCategory;
	
	private JButton btnSearchClerk;	
	private JButton btnAddClerk;
	private JButton btnUpdateClerk;
	private JButton btnDeleteClerk;
	private JButton btnSearchGoods;
	private JButton btnAddGoods;
	private JButton btnDeleteGoods;
	private JButton btnUpdateGoods;
	private JButton btnAddToCart;
	private JButton btnCheckout;
	private JButton btnChange;
	private JButton btnDelete;
	private JButton btnPayment;
	private JButton btnSales;
	private JButton btnAdd;
	private JButton btnExitSystem;
	private JButton btnLogout;
	private JButton btnSearch;
	
	private JTable tblClerk;
	private JTable tblGoods;
	private JTable tblBrowse;
	private JTable tblCheckout;
	private JTable tblSalesToday;
	
	private ListSelectionModel cellClerkSelectionModel;
	private ListSelectionModel cellGoodsSelectionModel;
	private ListSelectionModel cellBrowseSelectionModel;
	private ListSelectionModel cellCheckoutSelectionModel;	

	private JLabel msgLabel;
	private JLabel lblSearchGoodsColumnName;
	private JLabel lblSearchClerkColumnName;
	private JLabel lblBrowseTotalItemsAtCart;
	private JLabel lblCheckoutTotalItemsAtCart;
	private JLabel lblNumberOfItemsToChange;
	
	private String sID;
	private String sName;
	private String sPassword;
	
	private String gID;
	private String gName;
	private String gPrice;
	private String gNum;
	
	private String gIDBrowse;
	private String gNameBrowse;
	private String gPriceBrowse;
	private String gNumBrowse;
	
	private String gIDCart;
	private String gNameCart;
	private String gPriceCart;
	private String gNumCart;
	
	private String dml;

	public MainView() {
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 670;
	    int windowHeight = 600;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,windowHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Welcome to Shopping Management System");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font myFont = new Font("Arial", Font.PLAIN, 15);
		msgLabel= new JLabel();
		msgLabel.setFont(myFont);
		JOptionPane.setDefaultLocale(Locale.ENGLISH);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(myFont);
		tabbedPane.setBounds(0, 0, 652, 555);
		contentPane.add(tabbedPane);
		
		JPanel pnlSale = new JPanel();
		tabbedPane.addTab("Sale", null, pnlSale, null);
		pnlSale.setLayout(null);
		
		JScrollPane spBrowse = new JScrollPane();
		spBrowse.setBounds(14, 26, 428, 484);
		pnlSale.add(spBrowse);
		
		tblBrowse = new JTable();
		tblBrowse.setDefaultEditor(Object.class, null);
		tblBrowse.setRowHeight(35);
		tblBrowse.setFont(myFont);
		
		cellBrowseSelectionModel = tblBrowse.getSelectionModel();
		cellBrowseSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellBrowseSelectionModel.addListSelectionListener(new ListSelectionListener() 
	    {
	        public void valueChanged(ListSelectionEvent e) 
	        {
	        	int selectedRow = tblBrowse.getSelectedRow();
	        	if (selectedRow >= 0)
	        	{
	        		gIDBrowse = (String) tblBrowse.getValueAt(selectedRow, 0);
	        		gNameBrowse = (String) tblBrowse.getValueAt(selectedRow, 1);
	        		gPriceBrowse = Float.toString((Float) tblBrowse.getValueAt(selectedRow, 2));
	        		gNumBrowse = Integer.toString((Integer) tblBrowse.getValueAt(selectedRow, 3));
	        	}
	        }
	    });		

		
		tblBrowse.getTableHeader().setPreferredSize(new Dimension(10000, 35));
		tblBrowse.getTableHeader().setFont(myFont);	
		final TableCellRenderer tcrBrowseOs = tblBrowse.getTableHeader().getDefaultRenderer();
		tblBrowse.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) 
            {
            	JLabel lbl = (JLabel) tcrBrowseOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	            lbl.setHorizontalAlignment(SwingConstants.LEFT);
	            return lbl;
	        }
	    });				
		spBrowse.setViewportView(tblBrowse);	    
		
		cbbSelectItemNumberToCart = new JComboBox<String>();
		cbbSelectItemNumberToCart.setFont(myFont);
		cbbSelectItemNumberToCart.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
		cbbSelectItemNumberToCart.setBounds(456, 141, 153, 35);
		pnlSale.add(cbbSelectItemNumberToCart);
		
		btnAddToCart = new JButton("Add To Cart");
		btnAddToCart.setFont(myFont);
		btnAddToCart.setBounds(456, 237, 153, 35);
		pnlSale.add(btnAddToCart);
		
		btnCheckout = new JButton("Checkout");
		btnCheckout.setFont(myFont);
		btnCheckout.setBounds(456, 301, 153, 35);
		pnlSale.add(btnCheckout);
		
		lblBrowseTotalItemsAtCart = new JLabel("0 Cart");
		lblBrowseTotalItemsAtCart.setFont(new Font("Arial", Font.PLAIN, 20));
		lblBrowseTotalItemsAtCart.setBounds(464, 25, 153, 35);
		pnlSale.add(lblBrowseTotalItemsAtCart);
		
		JLabel lblNumberOfItemsToBuy = new JLabel("Number of  items to  add");
		lblNumberOfItemsToBuy.setFont(myFont);
		lblNumberOfItemsToBuy.setBounds(456, 98, 165, 35);
		pnlSale.add(lblNumberOfItemsToBuy);
		
		btnLogout = new JButton("Logout");
		btnLogout.setFont(myFont);
		btnLogout.setBounds(456, 416, 153, 35);
		pnlSale.add(btnLogout);
		
		btnExitSystem = new JButton("Exit System");
		btnExitSystem.setFont(myFont);
		btnExitSystem.setBounds(456, 464, 153, 35);
		pnlSale.add(btnExitSystem);
		
		JPanel pnlCheckout = new JPanel();
		tabbedPane.addTab("Checkout", null, pnlCheckout, null);
		pnlCheckout.setLayout(null);
			
		JScrollPane spCheckout = new JScrollPane();
		spCheckout.setBounds(14, 26, 428, 484);
		pnlCheckout.add(spCheckout);
	
		tblCheckout = new JTable();
		tblCheckout.setDefaultEditor(Object.class, null);
		tblCheckout.setRowHeight(35);
		tblCheckout.setFont(myFont);
		
		cellCheckoutSelectionModel = tblCheckout.getSelectionModel();
		cellCheckoutSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellCheckoutSelectionModel.addListSelectionListener(new ListSelectionListener() 
	    {
	        public void valueChanged(ListSelectionEvent e) 
	        {
	        	int selectedRow = tblCheckout.getSelectedRow();
	        	if (selectedRow >= 0)
	        	{
	        		gIDCart = (String) tblCheckout.getValueAt(selectedRow, 0);
	        		gNameCart = (String) tblCheckout.getValueAt(selectedRow, 1);
	        		gPriceCart = Float.toString((Float) tblCheckout.getValueAt(selectedRow, 2));
	        		gNumCart = Integer.toString((Integer) tblCheckout.getValueAt(selectedRow, 3));
	        	}
	        }
	    });		
		
		tblCheckout.getTableHeader().setPreferredSize(new Dimension(10000, 35));
		tblCheckout.getTableHeader().setFont(myFont);	
		final TableCellRenderer tcrCheckoutOs = tblCheckout.getTableHeader().getDefaultRenderer();
		tblCheckout.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) 
            {
            	JLabel lbl = (JLabel) tcrCheckoutOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	            lbl.setHorizontalAlignment(SwingConstants.LEFT);
	            return lbl;
	        }
	    });			
		spCheckout.setViewportView(tblCheckout);	
		
		lblCheckoutTotalItemsAtCart = new JLabel("0 Cart");
		lblCheckoutTotalItemsAtCart.setFont(new Font("Arial", Font.PLAIN, 20));
		lblCheckoutTotalItemsAtCart.setBounds(465, 28, 153, 35);
		pnlCheckout.add(lblCheckoutTotalItemsAtCart);
		
		cbbSelectItemsNumberToChange = new JComboBox<String>();
		cbbSelectItemsNumberToChange.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60"}));
		cbbSelectItemsNumberToChange.setFont(myFont);
		cbbSelectItemsNumberToChange.setBounds(456, 158, 153, 35);
		pnlCheckout.add(cbbSelectItemsNumberToChange);
		
		btnChange = new JButton("Change");
		btnChange.setFont(myFont);
		btnChange.setBounds(456, 206, 153, 35);
		pnlCheckout.add(btnChange);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(myFont);
		btnDelete.setBounds(456, 349, 153, 35);
		pnlCheckout.add(btnDelete);
		
		btnPayment = new JButton("Payment");
		btnPayment.setFont(myFont);
		btnPayment.setBounds(456, 397, 153, 35);
		pnlCheckout.add(btnPayment);
		
		lblNumberOfItemsToChange = new JLabel("Number of Items to update");
		lblNumberOfItemsToChange.setFont(myFont);
		lblNumberOfItemsToChange.setBounds(456, 104, 177, 35);
		pnlCheckout.add(lblNumberOfItemsToChange);
		
		btnSales = new JButton("Sales Page");
		btnSales.setFont(myFont);
		btnSales.setBounds(456, 475, 153, 35);
		pnlCheckout.add(btnSales);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(myFont);
		btnAdd.setBounds(456, 254, 153, 35);
		pnlCheckout.add(btnAdd);
		
		JPanel pnlSalesToday = new JPanel();
		tabbedPane.addTab("Sales Today", null, pnlSalesToday, null);
		pnlSalesToday.setLayout(null);
		
		JScrollPane spSalesToday = new JScrollPane();
		spSalesToday.setBounds(14, 24, 428, 486);
		pnlSalesToday.add(spSalesToday);
		
		
		tblSalesToday = new JTable();
		tblSalesToday.setDefaultEditor(Object.class, null);
		tblSalesToday.setRowHeight(35);
		tblSalesToday.setFont(myFont);
		
		tblSalesToday.getTableHeader().setPreferredSize(new Dimension(10000, 35));
		tblSalesToday.getTableHeader().setFont(myFont);	
		final TableCellRenderer tcrSalesTodayOs = tblSalesToday.getTableHeader().getDefaultRenderer();
		tblSalesToday.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) 
            {
            	JLabel lbl = (JLabel) tcrSalesTodayOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	            lbl.setHorizontalAlignment(SwingConstants.LEFT);
	            return lbl;
	        }
	    });		
		spSalesToday.setViewportView(tblSalesToday);		
		
		JLabel lblSalesByCategory = new JLabel("Sales by Category");
		lblSalesByCategory.setFont(myFont);
		lblSalesByCategory.setBounds(466, 26, 153, 35);
		pnlSalesToday.add(lblSalesByCategory);
		
		cbbSalesByCategory = new JComboBox<String>();
		cbbSalesByCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Clerk", "Goods"}));
		cbbSalesByCategory.setFont(myFont);
		cbbSalesByCategory.setBounds(466, 74, 153, 35);
		pnlSalesToday.add(cbbSalesByCategory);
		
		btnSearch = new JButton("Search");
		btnSearch.setFont(myFont);
		btnSearch.setBounds(466, 372, 153, 35);
		pnlSalesToday.add(btnSearch);
		
		JLabel lblNameUnderCategory = new JLabel("Name Under Category");
		lblNameUnderCategory.setFont(myFont);
		lblNameUnderCategory.setBounds(466, 151, 153, 35);
		pnlSalesToday.add(lblNameUnderCategory);
		
		cbbNameUnderCategory = new JComboBox<String>();
		cbbNameUnderCategory.setFont(myFont);
		cbbNameUnderCategory.setBounds(466, 199, 153, 35);
		pnlSalesToday.add(cbbNameUnderCategory);
		
		
		JPanel pnlGoodsAdmin = new JPanel();
		tabbedPane.addTab("Goods Admin", null, pnlGoodsAdmin, null);
		pnlGoodsAdmin.setLayout(null);
		
		JScrollPane spGoods = new JScrollPane();
		spGoods.setBounds(14, 24, 428, 486);
		pnlGoodsAdmin.add(spGoods);
		
		tblGoods = new JTable();
		tblGoods.setDefaultEditor(Object.class, null);
		tblGoods.setRowHeight(35);
		tblGoods.setFont(myFont);
		
		cellGoodsSelectionModel = tblGoods.getSelectionModel();
		cellGoodsSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellGoodsSelectionModel.addListSelectionListener(new ListSelectionListener() 
	    {
	        public void valueChanged(ListSelectionEvent e) 
	        {
	        	int selectedRow = tblGoods.getSelectedRow();
	        	if (selectedRow >= 0)
	        	{
	        		gID = (String) tblGoods.getValueAt(selectedRow, 0);
	        		gName = (String) tblGoods.getValueAt(selectedRow, 1);
	        		gPrice = Float.toString((Float) tblGoods.getValueAt(selectedRow, 2));
	        		gNum = Integer.toString((Integer) tblGoods.getValueAt(selectedRow, 3));
	    	  	}
	        }
	    });		
		
		tblGoods.getTableHeader().setPreferredSize(new Dimension(10000, 35));
		tblGoods.getTableHeader().setFont(myFont);	
		final TableCellRenderer tcrGoodsOs = tblGoods.getTableHeader().getDefaultRenderer();
		tblGoods.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) 
            {
            	JLabel lbl = (JLabel) tcrGoodsOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	            lbl.setHorizontalAlignment(SwingConstants.LEFT);
	            return lbl;
	        }
	    });		
		spGoods.setViewportView(tblGoods);		
		
		JPanel pnlSearchGoods = new JPanel();
		pnlSearchGoods.setLayout(null);
		pnlSearchGoods.setFont(myFont);
		pnlSearchGoods.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchGoods.setBounds(468, 24, 165, 231);
		pnlGoodsAdmin.add(pnlSearchGoods);
		
		txtSearchGoods = new JTextField();
		txtSearchGoods.setFont(myFont);
		txtSearchGoods.setColumns(10);
		txtSearchGoods.setBounds(5, 109, 153, 35);
		pnlSearchGoods.add(txtSearchGoods);
		
		btnSearchGoods = new JButton("Search Items");
		btnSearchGoods.setFont(myFont);
		btnSearchGoods.setBounds(5, 168, 153, 35);
		pnlSearchGoods.add(btnSearchGoods);
		
		lblSearchGoodsColumnName = new JLabel("Search Column Name");
		lblSearchGoodsColumnName.setFont(myFont);
		lblSearchGoodsColumnName.setBounds(5, 0, 153, 35);
		pnlSearchGoods.add(lblSearchGoodsColumnName);
		
		cbbSearchGoods = new JComboBox<String>();
		cbbSearchGoods.setModel(new DefaultComboBoxModel<String>(new String[] {"Item ID", "Item Name"}));
		cbbSearchGoods.setFont(myFont);
		cbbSearchGoods.setBounds(5, 48, 153, 35);
		pnlSearchGoods.add(cbbSearchGoods);
		
		JPanel pnlManageGoods = new JPanel();
		pnlManageGoods.setLayout(null);
		pnlManageGoods.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlManageGoods.setBounds(468, 279, 165, 231);
		pnlGoodsAdmin.add(pnlManageGoods);
		
		btnAddGoods = new JButton("Add an Item");
		btnAddGoods.setFont(myFont);
		btnAddGoods.setBounds(5, 13, 153, 35);
		pnlManageGoods.add(btnAddGoods);
		
		btnDeleteGoods = new JButton("Delete an Item");
		btnDeleteGoods.setFont(myFont);
		btnDeleteGoods.setBounds(5, 86, 153, 35);
		pnlManageGoods.add(btnDeleteGoods);
		
		btnUpdateGoods = new JButton("Update an Item");
		btnUpdateGoods.setFont(myFont);
		btnUpdateGoods.setBounds(5, 161, 153, 35);
		pnlManageGoods.add(btnUpdateGoods);
		
		JPanel pnlClerkAdmin = new JPanel();
		tabbedPane.addTab("Clerk Admin", null, pnlClerkAdmin, null);
		pnlClerkAdmin.setLayout(null);
		
		JScrollPane spClerk = new JScrollPane();
		spClerk.setBounds(14, 24, 428, 486);
		pnlClerkAdmin.add(spClerk);
		
		tblClerk = new JTable();
		tblClerk.setDefaultEditor(Object.class, null);
		tblClerk.setRowHeight(35);
		tblClerk.setFont(myFont);
		
		cellClerkSelectionModel = tblClerk.getSelectionModel();
		cellClerkSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cellClerkSelectionModel.addListSelectionListener(new ListSelectionListener() 
	    {
	        public void valueChanged(ListSelectionEvent e) 
	        {
	        	int selectedRow = tblClerk.getSelectedRow();
	        	if (selectedRow >= 0)
	        	{
	        		sID = (String) tblClerk.getValueAt(selectedRow, 0);
	        		sName = (String) tblClerk.getValueAt(selectedRow, 1);
	        		sPassword = (String) tblClerk.getValueAt(selectedRow, 2);
	        	}
	        }
	    });		
		
		tblClerk.getTableHeader().setPreferredSize(new Dimension(10000, 35));
		tblClerk.getTableHeader().setFont(myFont);	
		final TableCellRenderer tcrClerkOs = tblClerk.getTableHeader().getDefaultRenderer();
	    tblClerk.getTableHeader().setDefaultRenderer(new TableCellRenderer() 
	    {
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) 
            {
            	JLabel lbl = (JLabel) tcrClerkOs.getTableCellRendererComponent(table,value, isSelected, hasFocus, row, column);
	            lbl.setHorizontalAlignment(SwingConstants.LEFT);
	            return lbl;
	        }
	    });		
	    spClerk.setViewportView(tblClerk);
		
		JPanel pnlSearchClerk = new JPanel();
		pnlSearchClerk.setFont(myFont);
		pnlSearchClerk.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlSearchClerk.setBounds(468, 24, 165, 231);
		pnlClerkAdmin.add(pnlSearchClerk);
		pnlSearchClerk.setLayout(null);
		
		txtSearchClerk = new JTextField();
		txtSearchClerk.setFont(myFont);
		txtSearchClerk.setColumns(10);
		txtSearchClerk.setBounds(5, 109, 153, 35);
		pnlSearchClerk.add(txtSearchClerk);
		
		btnSearchClerk = new JButton("Search Clerks");
		btnSearchClerk.setFont(myFont);
		btnSearchClerk.setBounds(5, 168, 153, 35);
		pnlSearchClerk.add(btnSearchClerk);
		
		lblSearchClerkColumnName = new JLabel("Search Column Name");
		lblSearchClerkColumnName.setFont(myFont);
		lblSearchClerkColumnName.setBounds(5, 0, 153, 35);
		pnlSearchClerk.add(lblSearchClerkColumnName);
		
		cbbSearchClerk = new JComboBox<String>();
		cbbSearchClerk.setFont(myFont);
		cbbSearchClerk.setModel(new DefaultComboBoxModel<String>(new String[] {"Clerk ID", "Clerk Name"}));
		cbbSearchClerk.setBounds(5, 48, 153, 35);
		pnlSearchClerk.add(cbbSearchClerk);
		
		JPanel pnlManageClerk = new JPanel();
		pnlManageClerk.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlManageClerk.setBounds(468, 279, 165, 231);
		pnlClerkAdmin.add(pnlManageClerk);
		pnlManageClerk.setLayout(null);
		
		btnAddClerk = new JButton("Add a Clerk");
		btnAddClerk.setFont(myFont);
		btnAddClerk.setBounds(5, 13, 153, 35);
		pnlManageClerk.add(btnAddClerk);
		
		btnDeleteClerk = new JButton("Delete a Clerk");
		btnDeleteClerk.setFont(myFont);
		btnDeleteClerk.setBounds(5, 86, 153, 35);
		pnlManageClerk.add(btnDeleteClerk);
		
		btnUpdateClerk = new JButton("Update a Clerk");
		btnUpdateClerk.setFont(myFont);
		btnUpdateClerk.setBounds(5, 161, 153, 35);
		pnlManageClerk.add(btnUpdateClerk);
	}
	public void addMainViewItemListener(ItemListener listenForClick)
	{
		cbbSalesByCategory.addItemListener(listenForClick);
	}
	public void addMainViewListener(ActionListener listenForClick)
	{
		btnSearchClerk.addActionListener(listenForClick);
		btnAddClerk.addActionListener(listenForClick);
		btnUpdateClerk.addActionListener(listenForClick);
		btnDeleteClerk.addActionListener(listenForClick);
		btnSearchGoods.addActionListener(listenForClick);
		btnAddGoods.addActionListener(listenForClick);
		btnDeleteGoods.addActionListener(listenForClick);
		btnUpdateGoods.addActionListener(listenForClick);
		btnAddToCart.addActionListener(listenForClick);
		btnCheckout.addActionListener(listenForClick);
		btnChange.addActionListener(listenForClick);
		btnDelete.addActionListener(listenForClick);
		btnSales.addActionListener(listenForClick);
		btnPayment.addActionListener(listenForClick);
		btnAdd.addActionListener(listenForClick);
		btnExitSystem.addActionListener(listenForClick);
		btnLogout.addActionListener(listenForClick);
		btnSearch.addActionListener(listenForClick);
	}
	public JTable getContentTable(String tableName)
	{
		if (tableName == "SALESMAN") return tblClerk; 
		return tblGoods;
	}
	
	public JTable getBrowseTable()
	{
		return tblBrowse;		
	}

	public JTable getCheckoutTable()
	{
		return tblCheckout;		
	}
	public void getClerkReadyToDisplay(boolean hasRowsAtDB)
	{
		if (tblClerk.getRowCount() == 0) 
		{
			if (hasRowsAtDB)
			{
				lblSearchClerkColumnName.setEnabled(true);
				cbbSearchClerk.setEnabled(true);
				txtSearchClerk.setEnabled(true);
				btnSearchClerk.setEnabled(true);	
			}else
			{
				lblSearchClerkColumnName.setEnabled(false);
				cbbSearchClerk.setEnabled(false);
				txtSearchClerk.setEnabled(false);
				btnSearchClerk.setEnabled(false);				
			}
			btnAddClerk.setEnabled(true);
			btnDeleteClerk.setEnabled(false);
			btnUpdateClerk.setEnabled(false);					
		} else if (tblClerk.getRowCount() > 0)
		{
			tblClerk.setRowSelectionInterval(0, 0);			
			lblSearchClerkColumnName.setEnabled(true);
			cbbSearchClerk.setEnabled(true);
			txtSearchClerk.setEnabled(true);
			btnSearchClerk.setEnabled(true);
			btnDeleteClerk.setEnabled(true);
			btnUpdateClerk.setEnabled(true);					
		}

	}
	public void getGoodsReadyToDisplay(boolean hasRowsAtDB)
	{
		if (tblGoods.getRowCount() == 0) 
		{
			if (hasRowsAtDB)
			{
				lblSearchGoodsColumnName.setEnabled(true);
				cbbSearchGoods.setEnabled(true);
				txtSearchGoods.setEnabled(true);
				btnSearchGoods.setEnabled(true);	
			}else
			{
				lblSearchGoodsColumnName.setEnabled(false);
				cbbSearchGoods.setEnabled(false);
				txtSearchGoods.setEnabled(false);
				btnSearchGoods.setEnabled(false);					
			}
			btnAddGoods.setEnabled(true);
			btnDeleteGoods.setEnabled(false);
			btnUpdateGoods.setEnabled(false);					
		} else if (tblGoods.getRowCount() > 0)
		{
			tblGoods.setRowSelectionInterval(0, 0);			
			lblSearchGoodsColumnName.setEnabled(true);
			cbbSearchGoods.setEnabled(true);
			txtSearchGoods.setEnabled(true);
			btnSearchGoods.setEnabled(true);
			btnDeleteGoods.setEnabled(true);
			btnUpdateGoods.setEnabled(true);					
		}
	}
	public void getBrowseReadyToDisplay(boolean enabled)
	{
		btnAddToCart.setEnabled(enabled);
		btnCheckout.setEnabled(enabled);
		cbbSelectItemNumberToCart.setEnabled(enabled);
	}
	public void getCheckoutReadyToDisplay(boolean enabled)
	{
		cbbSelectItemsNumberToChange.setEnabled(enabled);
		btnAdd.setEnabled(enabled);
		btnChange.setEnabled(enabled);
		btnDelete.setEnabled(enabled);
		btnPayment.setEnabled(enabled);
	}
 	public String getSearchColumnName(String tableName)
	{
		String columnName = "";
		String returnColumnName = "";
		
		if (tableName == "SALESMAN")
		{
			columnName = cbbSearchClerk.getSelectedItem().toString();
			if (columnName.equals("Clerk ID"))
			{
				returnColumnName = "sid";
			} else
			{
				returnColumnName = "sname";
			}
		} else if (tableName == "GOODS")
		{
			columnName = cbbSearchGoods.getSelectedItem().toString();
			if (columnName.equals("Item ID"))
			{
				returnColumnName = "gid";
			} else
			{
				returnColumnName = "gname";
			}			
		}
		
		return returnColumnName;
	} 
		
	public String getSearchContent(String tableName)
	{
		String theSearchContent ="";
		
		if (tableName == "SALESMAN")
		{
			theSearchContent = txtSearchClerk.getText();			
		} else if (tableName == "GOODS")
		{
			theSearchContent = txtSearchGoods.getText();						
		}
		
		return theSearchContent;
	}
	public String getClerkID()
	{
		return sID;
	}
	public String getClerkName()
	{
		return sName;
	}
	public String getClerkPassword()
	{
		return sPassword;
	}
	
	public String getGoodsID()
	{
		return gID;
	}
	public String getGoodsName()
	{
		return gName;
	}
	public String getGoodsUnitPrice()
	{
		return gPrice;
	}
	public String getGoodsStockLevel()
	{
		return gNum;
	}
	public String getGoodsIDCart()
	{
		return gIDCart;
	}
	public String getGoodsNameCart()
	{
		return gNameCart;
	}
	public String getGoodsUnitPriceCart()
	{
		return gPriceCart;
	}
	public String getGoodsStockLevelCart()
	{
		return gNumCart;
	}
	public String getGoodsIDBrowse()
	{
		return gIDBrowse;
	}
	public String getGoodsNameBrowse()
	{
		return gNameBrowse;
	}
	public String getGoodsUnitPriceBrowse()
	{
		return gPriceBrowse;
	}
	public String getGoodsStockLevelBrowse()
	{
		return gNumBrowse;
	}
	public String getDML()
	{
		return dml;
	}
	public void setDML(String dml)
	{
		this.dml = dml;
	}
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	public String getNumberOfItemsToCart()
	{
		return cbbSelectItemNumberToCart.getSelectedItem().toString();
	}
	public String getNumberOfItemsToChange()
	{
		return cbbSelectItemsNumberToChange.getSelectedItem().toString();
	}
	public void OutOfStock()
	{
		msgLabel.setText("Out of stock!"); 
		JOptionPane.showMessageDialog(null,msgLabel ,"Sales Status",JOptionPane.ERROR_MESSAGE);
	}
	public Integer PaymentConfirm()
	{
		msgLabel.setText("Total payment: " +lblCheckoutTotalItemsAtCart.getText()); 
		return JOptionPane.showConfirmDialog(null,msgLabel ,"Please proceed payment!",JOptionPane.OK_CANCEL_OPTION);
	}
	public JLabel getBrowseLabel()
	{
		return lblBrowseTotalItemsAtCart;
	}
	public JLabel getCartLabel()
	{
		return lblCheckoutTotalItemsAtCart;
	}
	public String getSalesByCategoryString() 
	{
		return cbbSalesByCategory.getSelectedItem().toString();
	}
	public String getNameUnderCategoryString() 
	{
		return cbbNameUnderCategory.getSelectedItem().toString();
	}
	public JComboBox<String> getNameUnderCategory()
	{
		return cbbNameUnderCategory;		
	}
	public JTable getSalesTodayTable()
	{
		return tblSalesToday;		
	}
}
