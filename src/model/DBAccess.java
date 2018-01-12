package model;

import java.sql.*;
import net.proteanit.sql.DbUtils;

import view.LoginView;
import view.MainView;
import view.ClerkMgnView;
import view.GoodsMgnView;
import model.DBConnection;
import tools.MiscFunction;

public final class DBAccess {
	Connection        conn  = null;
	PreparedStatement pstmt = null;
	ResultSet 		  rs  	= null;
	
	private String theTempTableName;
	private String userID = "";
	
	public DBAccess()
	{
		theTempTableName = "TEMP" + MiscFunction.getIDByCurrentTime();
	}
	
	public String getUserIDByUserNamePassword(LoginView theLoginView)
	{
		conn = DBConnection.getconn();
		String sql = "SELECT SID FROM SALESMAN WHERE SNAME=? AND SPASSWORD=?";
				
		try
		{
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,theLoginView.getUserName());
			pstmt.setString(2,theLoginView.getPassword());	
			rs 	  = pstmt.executeQuery();
			
			rs.first();			
			if (rs.getRow() != 0) 
			{
				userID = rs.getString("sid");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
		return userID;
	}
	public Integer getCartStockByBrowseID(MainView theMainView)
	{
		conn = DBConnection.getconn();
				
		try
		{
			String sql = "SELECT gnum FROM " + theTempTableName + " WHERE gid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,theMainView.getGoodsIDBrowse());	
			rs 	  = pstmt.executeQuery();
			
			rs.first();			
			if (rs.getRow() == 0) return 0;
			return 	rs.getInt("gnum");			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public Integer getBrowseStockByCartID(MainView theMainView)
	{
		conn = DBConnection.getconn();
				
		try
		{
			String sql = "SELECT gnum FROM GOODS WHERE gid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,theMainView.getGoodsIDCart());	
			rs 	  = pstmt.executeQuery();
			
			rs.first();			
			return 	rs.getInt("gnum");			
		} catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void createAccount(LoginView theLoginView)
	{
		String sql;
		
		userID = MiscFunction.getIDByCurrentTime();
		conn = DBConnection.getconn();	
		
		try
		{
			sql = "INSERT INTO SALESMAN VALUES(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,userID);
			pstmt.setString(2,theLoginView.getUserName());
			pstmt.setString(3,theLoginView.getPassword());
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
	}
	public void insDelUpdGoods(GoodsMgnView theGoodsMgnView,String dml)
	{
		String sql;

		conn = DBConnection.getconn();	
	
		try
		{
			if (dml.equals("add"))
			{	
				sql = "INSERT INTO GOODS VALUES(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);	
				pstmt.setString(1,theGoodsMgnView.getGoodsID());
				pstmt.setString(2,theGoodsMgnView.getGoodsName());
				pstmt.setFloat(3,Float.valueOf(theGoodsMgnView.getGoodsUnitPrice()));
				pstmt.setInt(4,Integer.valueOf(theGoodsMgnView.getGoodsStockLevel()));
			} 
			else if (dml.equals("delete"))
			{
				sql = "DELETE FROM GOODS WHERE gid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theGoodsMgnView.getGoodsID());
			} 
			else if (dml.equals("update"))
			{
				sql = "UPDATE GOODS SET gname = ? , gprice = ?, gnum = ? WHERE gid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theGoodsMgnView.getGoodsName());
				pstmt.setFloat(2,Float.valueOf(theGoodsMgnView.getGoodsUnitPrice()));
				pstmt.setInt(3,Integer.valueOf(theGoodsMgnView.getGoodsStockLevel()));
				pstmt.setString(4,theGoodsMgnView.getGoodsID());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
	}
	public void insDelUpdGoodsBrowse(MainView theMainView,String dml,Integer theUpdateValue)
	{
		String sql;
			
		conn = DBConnection.getconn();	
	
		try
		{
			if (dml.equals("add"))
			{	
				sql = "INSERT INTO " + theTempTableName +" VALUES(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);	
				pstmt.setString(1,theMainView.getGoodsIDBrowse());
				pstmt.setString(2,theMainView.getGoodsNameBrowse());
				pstmt.setFloat(3,Float.valueOf(theMainView.getGoodsUnitPriceBrowse()));
				pstmt.setInt(4,Integer.valueOf(theMainView.getNumberOfItemsToCart()));
			} 
			else if (dml.equals("update"))
			{
				sql = "UPDATE " + theTempTableName + " SET gnum = ? WHERE gid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,theUpdateValue);
				pstmt.setString(2,theMainView.getGoodsIDBrowse());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
	}
	public void insDelUpdGoodsCart(MainView theMainView,String dml,Integer theUpdateValue) 
	{
		String sql;
			
		conn = DBConnection.getconn();	
	
		try
		{
			if (dml.equals("delete"))
			{
				sql = "DELETE FROM " + theTempTableName + " WHERE gid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theMainView.getGoodsIDCart());
			} 
			else if (dml.equals("update"))
			{
				sql = "UPDATE " + theTempTableName + " SET gnum = ? WHERE gid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,theUpdateValue);
				pstmt.setString(2,theMainView.getGoodsIDCart());
			}			
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
	}
	public void setBrowseLable(MainView theMainView)
	{
		String sql;
		int total = 0;
		
		conn = DBConnection.getconn();	
	
		try
		{
			sql = "SELECT SUM(gnum) as 'sum' FROM " + theTempTableName;
			pstmt = conn.prepareStatement(sql);
		    rs 	  = pstmt.executeQuery();			
			rs.first();			
			if (rs.getRow() != 0)
			{
				total = rs.getInt("sum");	
			}						
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
		theMainView.getBrowseLabel().setText(Integer.toString(total) + " Cart");
	}
    public void setCartLable(MainView theMainView)
	{
		String sql;
		float total = 0.00f;
		
		conn = DBConnection.getconn();	
	
		try
		{
			sql = "SELECT SUM(gnum*gprice) as 'sum' FROM " + theTempTableName;
			pstmt = conn.prepareStatement(sql);
		    rs 	  = pstmt.executeQuery();			
			rs.first();			
			if (rs.getRow() != 0)
			{
				total = rs.getFloat("sum");	
			}						
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
		theMainView.getCartLabel().setText("$ "+Float.toString(total));
	}
	public void createSalesCartTable()
	{
		String sql;
		StringBuilder sqlBuilder;
		
		sqlBuilder = new StringBuilder("CREATE TABLE " + theTempTableName + " (");
		sqlBuilder.append("gid     VARCHAR(20) NOT NULL,");
		sqlBuilder.append("gname   VARCHAR(20) NOT NULL,");
		sqlBuilder.append("gprice  FLOAT(18,2) NOT NULL,");
		sqlBuilder.append("gnum    INT NOT NULL,");
		sqlBuilder.append("PRIMARY KEY (gid)");
		sqlBuilder.append(" )ENGINE=INNODB;");

		conn = DBConnection.getconn();	
	
		try
		{
			sql = sqlBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}		
	}
	public void dropSalesCartTable()
	{
		String sql;

		conn = DBConnection.getconn();	
	
		try
		{
			sql = "DROP TABLE " + theTempTableName;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}		
	}
	public void loadTable(MainView theMainView)
	{
		displayTable(theMainView, "SALESMAN");
		if (theMainView.getContentTable("SALESMAN").getRowCount() > 0)
		{
			theMainView.getContentTable("SALESMAN").setRowSelectionInterval(0, 0);
			theMainView.getClerkReadyToDisplay(true);
		} else
		{
			theMainView.getClerkReadyToDisplay(false);
		}
				
		displayBrowseTable(theMainView);		
		if (theMainView.getBrowseTable().getRowCount() > 0)
		{
			theMainView.getBrowseTable().setRowSelectionInterval(0, 0);
			theMainView.getBrowseReadyToDisplay(true);
		} else
		{
			theMainView.getBrowseReadyToDisplay(false);
		}
		
		displayTable(theMainView, "GOODS");		
		if (theMainView.getContentTable("GOODS").getRowCount() > 0)
		{
			theMainView.getContentTable("GOODS").setRowSelectionInterval(0, 0);
			theMainView.getGoodsReadyToDisplay(true);
		} else
		{
			theMainView.getGoodsReadyToDisplay(false);
		}
		
		displayCheckoutTable(theMainView);
		if (theMainView.getCheckoutTable().getRowCount() > 0)
		{
			theMainView.getCheckoutTable().setRowSelectionInterval(0, 0);
			theMainView.getCheckoutReadyToDisplay(true);
		} else
		{
			theMainView.getCheckoutReadyToDisplay(false);
		}
		setBrowseLable(theMainView);
		setCartLable(theMainView);
		packNameUnderCategory(theMainView);
		dailySales(theMainView);
	}
	public void insDelUpdSalesMan(ClerkMgnView theClerkMgnView,String dml)
	{
		String sql;
				
		conn = DBConnection.getconn();	
		
		try
		{
			if (dml.equals("add"))
			{
				sql = "INSERT INTO SALESMAN VALUES(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theClerkMgnView.getClerkID());
				pstmt.setString(2,theClerkMgnView.getClerkName());
				pstmt.setString(3,theClerkMgnView.getClerkPassword());
			} 
			else if (dml.equals("delete"))
			{
				sql = "DELETE FROM SALESMAN WHERE sid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theClerkMgnView.getClerkID());
			} 
			else if (dml.equals("update"))
			{
				sql = "UPDATE SALESMAN SET sname = ? , spassword = ? WHERE sid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,theClerkMgnView.getClerkName());
				pstmt.setString(2,theClerkMgnView.getClerkPassword());
				pstmt.setString(3,theClerkMgnView.getClerkID());
			}
			pstmt.executeUpdate();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.addClose(pstmt,conn);
		}
	}
	public void queryTable(MainView theMainView, String tableName)
	{
		StringBuilder sqlBuilder = new StringBuilder("");
		String sql;
		
		conn = DBConnection.getconn();
		
		if (tableName == "SALESMAN")
		{
			sqlBuilder = new StringBuilder("SELECT sid as 'Clerk ID', sname as 'Clerk Name', spassword as 'Password' FROM ");			
		} else if (tableName == "GOODS")
		{
			sqlBuilder = new StringBuilder("SELECT gid as 'Item ID', gname as 'Item Name', gprice as 'Unit Price', gnum as 'Stock Level' FROM ");
		}
		sqlBuilder.append(tableName);
		sqlBuilder.append(" WHERE ");
		sqlBuilder.append(theMainView.getSearchColumnName(tableName));
		sqlBuilder.append(" LIKE '%");
		sqlBuilder.append(theMainView.getSearchContent(tableName));
		sqlBuilder.append("%'");
		sql = sqlBuilder.toString();
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			theMainView.getContentTable(tableName).setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void displayTable(MainView theMainView, String tableName)
	{
		String sql ="";
		conn = DBConnection.getconn();
		if (tableName.equals("SALESMAN"))
		{
			sql = "SELECT sid as 'Clerk ID', sname as 'Clerk Name', spassword as 'Password' FROM " + tableName;
		}else if (tableName.equals("GOODS"))	
		{
			sql = "SELECT gid as 'Item ID', gname as 'Item Name', gprice as 'Unit Price', gnum as 'Stock Level' FROM " + tableName;			
		}
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			theMainView.getContentTable(tableName).setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void displayBrowseTable(MainView theMainView)
	{
		String sql ="";
		conn = DBConnection.getconn();
		sql = "SELECT gid as 'Item ID', gname as 'Item Name', gprice as 'Unit Price', gnum as 'Stock Level' FROM GOODS";			
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			theMainView.getBrowseTable().setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void displayCheckoutTable(MainView theMainView)
	{
		String sql ="";
		conn = DBConnection.getconn();
		sql = "SELECT gid as 'Item ID', gname as 'Item Name', gprice as 'Unit Price', gnum as 'Stock Level' FROM " + theTempTableName;			
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			theMainView.getCheckoutTable().setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public int getTableRowCount(String tableName)
	{
		int tableRowCount = -1;
		conn = DBConnection.getconn();
		
		String sql = "SELECT COUNT(*) AS rowcount FROM " + tableName;
				
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			rs.next();
			tableRowCount = rs.getInt("rowcount");
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
		
		return tableRowCount;
	}
	public void doPayment()
	{
		conn = DBConnection.getconn();		
		String sql = "SELECT gid, gnum FROM " + theTempTableName;		

		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();

			sql = "INSERT INTO GSALES (gsid,gid,sid,snum) VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);	
			while (rs.next())
			{
				pstmt.setString(1,MiscFunction.getIDByCurrentTime());
				pstmt.setString(2,rs.getString("gid"));
				pstmt.setString(3,userID);
				pstmt.setInt(4,rs.getInt("gnum"));
				pstmt.executeUpdate();
			}
			pstmt.executeUpdate("UPDATE GOODS g, " + theTempTableName + " t SET g.gnum = g.gnum -t.gnum WHERE g.gid = t.gid");
			pstmt.executeUpdate("DELETE FROM " + theTempTableName);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void packNameUnderCategory(MainView theMainView)
	{
		StringBuilder sqlBuilder = new StringBuilder("");
		
		if (theMainView.getSalesByCategoryString() == "Clerk")
		{
			sqlBuilder.append("SELECT DISTINCT SALESMAN.sname as 'name'  ");
			sqlBuilder.append("FROM GSALES, SALESMAN  ");
			sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
		}else
		{
			sqlBuilder.append("SELECT DISTINCT GOODS.gname as 'name'  ");
			sqlBuilder.append("FROM GSALES, GOODS  "); 
			sqlBuilder.append("WHERE GOODS.gid = GSALES.gid  ");
		}
		sqlBuilder.append("AND GSALES.sdate> CURDATE()");
		String sql = sqlBuilder.toString();
		theMainView.getNameUnderCategory().removeAllItems();
		theMainView.getNameUnderCategory().addItem("Total");
		conn = DBConnection.getconn();
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs 	  = pstmt.executeQuery();
			while (rs.next())
			{
				theMainView.getNameUnderCategory().addItem(rs.getString("name"));
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public boolean hasDailySales(MainView theMainView)
	{
		StringBuilder sqlBuilder = new StringBuilder("");
		
		sqlBuilder.append("SELECT COUNT(*) AS rowcount ");			
		sqlBuilder.append("FROM GSALES, SALESMAN, GOODS ");
		sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
		sqlBuilder.append("AND GOODS.gid = GSALES.gid ");
		sqlBuilder.append("AND sdate> CURDATE() ");
		String sql = sqlBuilder.toString();

		conn = DBConnection.getconn();
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("rowcount")>0 ;

		} catch (SQLException e)
		{
			e.printStackTrace();
			return false;
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}

	}
	public void dailySalesByGoods(MainView theMainView)
	{
		boolean theHasDailySales = hasDailySales(theMainView);
		
		StringBuilder sqlBuilder = new StringBuilder("");
		
		sqlBuilder.append("SELECT SALESMAN.sname as 'Clerk', ");		
		sqlBuilder.append("sum(GSALES.snum) as 'Number', ");
		sqlBuilder.append("sum(GSALES.snum * GOODS.gprice) as 'Sub Total' ");
		sqlBuilder.append("FROM GSALES, SALESMAN, GOODS ");
		sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
		sqlBuilder.append("AND GOODS.gid = GSALES.gid ");
		
		if (theMainView.getNameUnderCategoryString() != "Total")
		{
			sqlBuilder.append("AND GOODS.gname = ? ");	
		}
		sqlBuilder.append("AND sdate> CURDATE() ");
		sqlBuilder.append("GROUP BY Clerk ");			
		
		
		if (theHasDailySales)
		{
			sqlBuilder.append("Union ");
			sqlBuilder.append("SELECT 'Total' as 'Clerk', ");			
			sqlBuilder.append("sum(GSALES.snum) as 'Number', ");
			sqlBuilder.append("sum(GSALES.snum * GOODS.gprice) as 'Sub Total' ");
			sqlBuilder.append("FROM GSALES, SALESMAN, GOODS ");
			sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
			sqlBuilder.append("AND GOODS.gid = GSALES.gid ");
			
			if (theMainView.getNameUnderCategoryString() != "Total")
			{
				sqlBuilder.append("AND GOODS.gname = ? ");
			}
			sqlBuilder.append("AND sdate> CURDATE() ");
			sqlBuilder.append("GROUP BY Clerk ");
		}
		String sql = sqlBuilder.toString();

		conn = DBConnection.getconn();
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			
			if (theMainView.getNameUnderCategoryString() != "Total")
			{
				pstmt.setString(1,theMainView.getNameUnderCategoryString());
				if (theHasDailySales)
				{
					pstmt.setString(2,theMainView.getNameUnderCategoryString());
				}
			}
			rs = pstmt.executeQuery();
			theMainView.getSalesTodayTable().setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
	public void dailySalesByClerk(MainView theMainView)
	{
		boolean theHasDailySales = hasDailySales(theMainView);
		
		StringBuilder sqlBuilder = new StringBuilder("");
		
		sqlBuilder.append("SELECT GOODS.gname as 'Goods', ");			
		sqlBuilder.append("sum(GSALES.snum) as 'Number', ");
		sqlBuilder.append("sum(GSALES.snum * GOODS.gprice) as 'Sub Total' ");
		sqlBuilder.append("FROM GSALES, SALESMAN, GOODS ");
		sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
		sqlBuilder.append("AND GOODS.gid = GSALES.gid ");
		if (theMainView.getNameUnderCategoryString() != "Total")
		{
			sqlBuilder.append("AND SALESMAN.sname = ?  ");
		}
		sqlBuilder.append("AND sdate> CURDATE() ");
		sqlBuilder.append("GROUP BY Goods ");
				
		if (theHasDailySales)
		{
			sqlBuilder.append("Union ");
			sqlBuilder.append("SELECT 'Total' as 'Goods', ");
			sqlBuilder.append("sum(GSALES.snum) as 'Number', ");
			sqlBuilder.append("sum(GSALES.snum * GOODS.gprice) as 'Sub Total' ");
			sqlBuilder.append("FROM GSALES, SALESMAN, GOODS ");
			sqlBuilder.append("WHERE SALESMAN.sid = GSALES.sid  ");
			sqlBuilder.append("AND GOODS.gid = GSALES.gid ");
			if (theMainView.getNameUnderCategoryString() != "Total")
			{
				sqlBuilder.append("AND SALESMAN.sname = ?  ");
			}
			sqlBuilder.append("AND sdate> CURDATE() ");
			sqlBuilder.append("GROUP BY Goods ");
		}
		
		String sql = sqlBuilder.toString();

		conn = DBConnection.getconn();
		
		try
		{
			pstmt = conn.prepareStatement(sql);
			
			if (theMainView.getNameUnderCategoryString() != "Total")
			{
				pstmt.setString(1,theMainView.getNameUnderCategoryString());
				if (theHasDailySales)
				{
					pstmt.setString(2,theMainView.getNameUnderCategoryString());
				}
			}
			rs = pstmt.executeQuery();
			theMainView.getSalesTodayTable().setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			DBConnection.queryClose(pstmt, rs, conn);
		}
	}
    public void dailySales(MainView theMainView)
    {
    	if (theMainView.getSalesByCategoryString() == "Clerk")
    	{
    		dailySalesByClerk(theMainView);
    	}else if (theMainView.getSalesByCategoryString() == "Goods")
    	{
    		dailySalesByGoods(theMainView);	
    	}
    }
}

