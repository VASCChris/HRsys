package mail;

public class MailThread extends Thread{

	
    private MailService mailService;
	private String mailTo;
	private String content;
	private String mailTitle;
	
	public MailThread(String mailTo, String content, String mailTitle, MailService mailService) {
		this.mailService = mailService;
		this.mailTo = mailTo;
		this.content = content;
		this.mailTitle = mailTitle;
	}
	

	@Override
	public void run() {
		mailService.sendMail("isoformsys@vascreative.com", mailTo, mailTitle, content);
	}

	
	
	
}
