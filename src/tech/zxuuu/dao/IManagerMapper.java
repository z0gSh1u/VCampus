package tech.zxuuu.dao;

import tech.zxuuu.entity.Manager;

public interface IManagerMapper {

	public Boolean verifyManager(Manager manager);
	
	public Manager getManagerDetailByCardNumber(String cardNumber); 
	
}
