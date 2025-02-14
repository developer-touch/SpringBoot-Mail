package com.ankit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ankit.sbean.PurchaseMgmtServiceImpl;

@Component
public class SpringBootMailRunnerTest implements CommandLineRunner{

	@Autowired
	private PurchaseMgmtServiceImpl purchaseService;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			String mailMessage = purchaseService.shopping(new String[] {"Coat","Bluthooth","T-shirt"}, new Double[] {23000.0,1300.0,2000.0}, 
					new String[] {"ankitraj7722@gmail.com","tanuy3357@gmail.com"});
			System.out.println(mailMessage);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
