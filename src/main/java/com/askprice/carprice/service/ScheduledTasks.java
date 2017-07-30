package com.askprice.carprice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.askprice.carprice.common.util.mail.EmailTools;

@Component
public class ScheduledTasks {
	
	@Autowired
	private CarPriceService carService;
	
	@Autowired
	private EmailTools email;

    @Scheduled(cron="0 15 1 * * ?")
    public void sendAskRequest() {
        try {
			carService.ExportRecord();
			email.sendEmailWithReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
