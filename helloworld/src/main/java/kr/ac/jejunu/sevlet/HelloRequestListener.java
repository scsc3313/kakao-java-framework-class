package kr.ac.jejunu.sevlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by HSH on 2016. 5. 10..
 */
public class HelloRequestListener implements ServletRequestListener {

    private final static Logger logger = LoggerFactory.getLogger(HelloRequestListener.class);

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        logger.info("************* request destroy *************");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        logger.info("************* request init *************");
    }
}
