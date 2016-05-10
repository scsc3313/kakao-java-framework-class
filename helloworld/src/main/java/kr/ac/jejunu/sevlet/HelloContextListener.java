package kr.ac.jejunu.sevlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by HSH on 2016. 5. 10..
 */
public class HelloContextListener implements ServletContextListener {

    private final static Logger logger = LoggerFactory.getLogger(HelloContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("*********** context int **************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("*********** context destroy **************");
    }
}
