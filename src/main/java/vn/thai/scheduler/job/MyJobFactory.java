package vn.thai.scheduler.job;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.thai.scheduler.service.LoggingTimeService;

@Component
public class MyJobFactory implements JobFactory {

  @Autowired LoggingTimeService schedulingService;

  @Autowired
  MyJobExcecuterService myJob;

  public Job newJob(TriggerFiredBundle bundle, Scheduler Scheduler) throws SchedulerException {
    return myJob;
  }
}
