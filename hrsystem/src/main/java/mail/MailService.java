package mail;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendMail(String from, String to, String subject, String msg) {
		MimeMessage message = mailSender.createMimeMessage();
 
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
 
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(msg);
 
		}
		catch (MessagingException e) {
			throw new MailParseException(e);
		}
 
		mailSender.send(message);
	}
	
//	public static void main(String[] args) {
//
//		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
//		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
//		sessionFactory.getCurrentSession().beginTransaction();
//		MailService mailService = (MailService)context.getBean("mailService");
//		
//		System.out.println("=======TEST=========");
//        mailService.sendMail("chris.chiu@vascreative.com", "chris.chiu@vascreative.com", "hehehe", "hahaha");;
//		System.out.println("================");
//		
//		sessionFactory.getCurrentSession().getTransaction().commit();
//		((ConfigurableApplicationContext)context).close();
//	}
}
