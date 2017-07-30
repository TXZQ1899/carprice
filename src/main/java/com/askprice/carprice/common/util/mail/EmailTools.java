package com.askprice.carprice.common.util.mail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.askprice.carprice.dao.MailListDao;
import com.askprice.carprice.entity.MailList;

@Component
public class EmailTools {
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
    private MailListDao mailDao;
	
	@Value("${spring.mail.username}")
    private String Sender;
	
//	@Value("${export.file.name}")
    private static String fileName = "询价记录";
	
	@Value("${export.file.folder}")
    private String folder;
	
	private static SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public void sendEmailWithReport() 
	{
		Iterator<MailList> iterator = mailDao.findAll().iterator();
		while (iterator.hasNext()) 
		{
			sendEmail(iterator.next().getMail());
		}
	}
	
	private void sendEmail(String sendTo) 
	{
		MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            String previousDay = getDateBefore(1);
            helper.setFrom(Sender);
            helper.setTo(sendTo);
            helper.setSubject("询价记录:" + previousDay);
            helper.setText("询价记录详情请见附件");
            String outputName = fileName + "_" + previousDay + ".xls";
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(folder + outputName));
            //加入邮件
            String attachmentName = outputName.replace("-", "_");
            System.out.println(attachmentName);
            helper.addAttachment(fileName + previousDay.substring(5).replace("-", "") + ".xls", file, "application/vnd.ms-excel");
        } catch (Exception e){
            e.printStackTrace();
        }
        mailSender.send(message);
	}
	
	public static String getDateBefore(Integer days) 
	{
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -days);
		return dtFormat.format(c.getTime());
	}

}
