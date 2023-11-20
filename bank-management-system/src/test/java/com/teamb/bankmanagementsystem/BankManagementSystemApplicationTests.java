package com.teamb.bankmanagementsystem;

import com.teamb.bankmanagementsystem.controller.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//Smoke and Sanity Testing
@RunWith(SpringRunner.class)
@SpringBootTest
public class BankManagementSystemApplicationTests {

	@Autowired
	DepositController depositController;
	@Autowired
	LoginController loginController;
	@Autowired
	RegistrationController registrationController;
	@Autowired
	TransferController transferController;
	@Autowired
	ViewBalanceController viewBalanceController;
	@Autowired
	ViewStatementController viewStatementController;
	@Autowired
	WithdrawController withdrawController;
	@Test
	public void contextLoads() {
		Assert.assertNotNull(depositController);
		Assert.assertNotNull(loginController);
		Assert.assertNotNull(registrationController);
		Assert.assertNotNull(transferController);
		Assert.assertNotNull(viewBalanceController);
		Assert.assertNotNull(viewStatementController);
		Assert.assertNotNull(withdrawController);
	}

}
