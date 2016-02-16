package zorg.hello.quartz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String pathroot = System.getProperty("user.dir");
		String file = pathroot + "/target/classes/z/hello/quartz/quartzbean.xml";
		System.out.println("Test start.");
		@SuppressWarnings({ "unused", "resource" })
		ApplicationContext context = new ClassPathXmlApplicationContext(file);
		// 如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
		// context.getBean("startQuertz");
		System.out.print("Test end..");
	}
}