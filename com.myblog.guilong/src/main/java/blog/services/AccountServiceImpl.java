package blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.AccountDao;
import blog.domain.Account;

@Service 
public class AccountServiceImpl implements AccountService{
   
	private static int nextAccountNumber=10000000;
	@Autowired
   private AccountDao accountDao;

	
	private int accountGenerator() {
		return nextAccountNumber+1;
	}
   
	@Override
	public Account createAccount() {
		Account account=new Account();
		account.setAccountNumber(accountGenerator());
	    accountDao.save(account);
	    return accountDao.findByAccountNumber(account.getAccountNumber());
	}
     
}
