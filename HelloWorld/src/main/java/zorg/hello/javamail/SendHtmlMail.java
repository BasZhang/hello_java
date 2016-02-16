package zorg.hello.javamail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHtmlMail {

	String SMTPHost = "";
	String user = "";
	String password = "";
	String from = "";
	String to = "";
	String subject = "";
	String content = "";

	public SendHtmlMail() {

	}

	public String getSMTPHost() {
		return SMTPHost;
	}

	public void setSMTPHost(String host) {
		SMTPHost = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		try {
			subject = new String(subject.getBytes("ISO8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		try {
			content = new String(content.getBytes("ISO8859-1"), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.content = content;
	}

	public boolean send() {
		// 创建一个属性对象
		Properties props = new Properties();
		// 指定smtp服务器
		props.put("mail.smtp.host", SMTPHost);
		// 指定是否需要smtp验证
		props.put("mail.smtp.auth", "true");
		try {
			// 创建一个授权验证对象
			SmtpAuth auth = new SmtpAuth();
			auth.setAccount(user, password);
			// 创建一个session对象
			Session mailSession = Session.getDefaultInstance(props);
			mailSession.setDebug(true);
			// 创建一个Message对象
			Message message = new MimeMessage(mailSession);
			// 指定发件人邮箱
			message.setFrom(new InternetAddress(from));
			// 指定收件人邮箱
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 指定邮箱主题
			message.setSubject(subject);
			// 指定邮箱内容及ContentType和编码方式
			message.setContent(content, "text/html;charset=utf-8");
			// 指定邮件发送日期
			message.setSentDate(new Date());
			// 指定邮件优先级 1:紧急 3:普通 5:缓慢
			message.setHeader("X-Priority", "1");
			message.saveChanges();
			// 创建一个Transport对象
			Transport transport = mailSession.getTransport("smtp");
			// 连接SMTP服务器
			transport.connect(SMTPHost, user, password);
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String[] args) {
		SendHtmlMail sender = new SendHtmlMail();
		sender.content = "test";
		sender.from = "zhangbo@ourpalm.com";
		sender.SMTPHost = "smtp.qiye.163.com";
		sender.subject = "test";
		sender.to = "zhangbo@ourpalm.com";
		sender.user = "zhangbo@ourpalm.com";
		sender.password = "ourpalm.com";
		sender.send();
	}
}