package vn.thai.scheduler.job;

import java.util.concurrent.atomic.AtomicLong;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.thai.scheduler.service.LoggingTimeService;

@Component
public class MyJobExcecuterService implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(MyJobExcecuterService.class);

  private static final AtomicLong counter = new AtomicLong(0);

  @Autowired private LoggingTimeService schedulingService;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    LOGGER.info("the job excecuting counter {}", counter.getAndIncrement());
    this.schedulingService.logTheTime();
  }
}
