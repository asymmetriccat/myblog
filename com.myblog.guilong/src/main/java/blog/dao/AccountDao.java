package blog.dao;

import org.springframework.data.repository.CrudRepository;

import blog.domain.Account;

public interface AccountDao extends CrudRepository<Account, Long> {
     Account findByAccountNumber(int accountNumber);
}
