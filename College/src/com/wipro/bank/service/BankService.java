package com.wipro.bank.service;
import java.sql.Connection;
import java.sql.SQLException;
import com.wipro.bank.been.*;
import com.wipro.bank.dao.*;
import com.wipro.bank.util.DBUtil;
//import com.wipro.bank.util.InsuffcientFundsException;
import com.wipro.bank.util.InsufficientFundsException;
public class BankService {
	 BankDAO d= new BankDAO();
 public  String checkBalance(String accountNumber) {
	 boolean flag = d.validateAccount(accountNumber);
		 if(flag) {
			 float balance=d.findBalance(accountNumber);
			 return "Balance:"+balance;
		 }
		 else {
			 return "INVALID BALANCE";
		 }	 
 }public String transfer(TransferBean transferBean) {
	 String fromAcc = transferBean.getFromAccountNumber();
     String toAcc = transferBean.getToAccountNumber();
     float amount = transferBean.getAmount();

     if (!d.validateAccount(fromAcc) || !d.validateAccount(toAcc)) {
         return "INVALID ACCOUNT";
     }

     float fromBalance = d.findBalance(fromAcc);

     try {
         if (fromBalance < amount) {
             throw new InsufficientFundsException();
         }

         d.updateBalance(fromAcc, fromBalance - amount);

         float toBalance = d.findBalance(toAcc);
         d.updateBalance(toAcc, toBalance + amount);

         boolean status = d.transferMoney(transferBean);

         if (status) {
             return "TRANSFER SUCCESSFUL";
         } else {
             return "TRANSFER FAILED";
         }

     } catch (InsufficientFundsException e) {
         return e.toString();
}
} 
	 
	 
 }
