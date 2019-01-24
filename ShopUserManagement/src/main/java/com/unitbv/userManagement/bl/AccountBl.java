package com.unitbv.userManagement.bl;

import com.unitbv.userManagement.dal.AccountDal;
import com.unitbv.userManagement.dao.Person;

public class AccountBl {
	private static final String persistanceUnitName = "dbSource";
	private AccountDal accountDal;
	
	public AccountBl() {
		accountDal  = new AccountDal(persistanceUnitName);
	}
	
	public boolean insert(Person person) {
		if (person != null) {
			return accountDal.insert(person);
		}
		return false;
	}

	public Person login(Person person){
		if(person!=null){
			return accountDal.login(person);
		}
		return null;
	}

}
