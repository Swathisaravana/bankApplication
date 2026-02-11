package com.wipro.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wipro.bank.been.TransferBean;
import com.wipro.bank.util.DBUtil;

public class BankDAO {
  public int generateSequenceNumber() {
	  return 0;
  }
  public boolean validateAccount(String accountNumber) 
  {
	  Connection con = DBUtil.getDBConnection();
	  String q1="SELECT 1 FROM ACCOUNT_TBL WHERE Account_Number = ?";
		    try {
		       // Connection con = DBUtil.getDBConnection();
		        PreparedStatement ps = con.prepareStatement(q1);
		        ps.setString(1, accountNumber);
		        ResultSet rs = ps.executeQuery();
		        while(rs.next()) {
		        return true;
		        }
		        rs.close();
		        ps.close();
		        con.close();     
		    } 
		        
		        
		    catch (Exception e) {
		        e.printStackTrace();
		    }
		    return false;
		}
  public float findBalance(String accountNumber) {
	  Connection con = DBUtil.getDBConnection();
	  String q2="SELECT Balance FROM ACCOUNT_TBL WHERE Account_Number = ?";
	  try {
	  PreparedStatement stmt = con.prepareStatement(q2);
      stmt.setString(1, accountNumber);
      ResultSet res = stmt.executeQuery();
      while(res.next()) {
    	  return res.getFloat(1);
      }
      res.close();
      stmt.close();
      con.close();
	  }
	  catch(SQLException e){
		  return -1;  
	  }
	  return -1;
}
public boolean transferMoney(TransferBean transferBean) {
	  Connection con = DBUtil.getDBConnection();
	 int transactionId = generateSequenceNumber();
	  String q3 = "INSERT INTO TRANSFER_TBL " +
	             "(Transaction_ID, Account_Number,Beneficiary_Account_number,  Transaction_Date,Transaction_Amount ) " +
	             "VALUES (TRANSFER_SEQ.NEXTVAL, ?, ?, ?, ?)";
	  try {
		

		  PreparedStatement stmt1 = con.prepareStatement(q3);
		  stmt1.setString(1,transferBean.getFromAccountNumber());
		  stmt1.setString(2, transferBean.getToAccountNumber());
		  stmt1.setDate(3, new java.sql.Date(System.currentTimeMillis()));
		  stmt1.setFloat(4, transferBean.getAmount());
		 
		  int rows = stmt1.executeUpdate();
		 
  if (rows > 0 ) {
	  return true;
  }
  con.close();
  stmt1.close();
		     
	  }
	  catch (Exception e){
		  e.printStackTrace();  
	  }
	  return false;
  }
  
  public boolean updateBalance(String accountNumber,float newBalance) {
	  try {
			Connection connection = DBUtil.getDBConnection();
			String q4 ="UPDATE ACCOUNT_TBL SET Balance=? where Account_Number=?";
		 PreparedStatement ps= connection.prepareStatement(q4);
		ps.setFloat(1, newBalance);	
		 ps.setString(2, accountNumber);
			 int rowsupdate= ps.executeUpdate();
			 if(rowsupdate>0) {
				 return true;
			 }
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	return false;
  }
  }

