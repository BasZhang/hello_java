package zorg.hello.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.access.joran.JoranConfigurator;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

public class MyApp3 {
	final static Logger logger = LoggerFactory.getLogger(MyApp3.class);

	public static void main(String[] args) { // assume SLF4J is bound to logback
												// in the current environment
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		try {
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(lc); // the context was probably already
											// configured by default
			// configuration rules
			lc.reset();
			configurator.doConfigure(args[0]);
		} catch (JoranException je) { // StatusPrinter will handle this

		}
		StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
		logger.info("Entering application.");
		Foo foo = new Foo();
		foo.doIt();
		logger.info("Exiting application.");
	}
}
