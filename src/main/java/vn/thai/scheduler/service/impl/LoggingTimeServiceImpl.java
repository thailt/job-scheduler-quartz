package vn.thai.scheduler.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.thai.scheduler.service.LoggingTimeService;

@Service
public class LoggingTimeServiceImpl implements LoggingTimeService {

    private static final Logger log = LoggerFactory.getLogger(LoggingTimeServiceImpl.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");


    public void logTheTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }

}
