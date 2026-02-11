package com.wipro.bank.main;
import com.wipro.bank.been.TransferBean;
import com.wipro.bank.service.BankService;
public class Main {
        public static void main(String[] args) {
        	BankService bankservice=new BankService();
        	System.out.println(bankservice.checkBalance("123456789012"));
        	TransferBean transferBean=new TransferBean();
        	transferBean.setFromAccountNumber("123456789012");
        	transferBean.setAmount(50000);
        	transferBean.setToAccountNumber("234567789012");
        	transferBean.setDateOfTransaction(new java.util.Date());
        	System.out.println(bankservice.transfer(transferBean));
        	
        	
        }
}
