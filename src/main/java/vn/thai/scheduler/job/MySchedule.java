package vn.thai.scheduler.job;

import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MySchedule {

  private static final Logger LOGGER = LoggerFactory.getLogger(MySchedule.class);

  private Scheduler myScheduler;

  public MySchedule(MyJobFactory myJobFactory) {
    SchedulerFactory schedFact = new StdSchedulerFactory();

    try {
      myScheduler = schedFact.getScheduler();
      myScheduler.setJobFactory(myJobFactory);
      myScheduler.start();
    } catch (SchedulerException e) {
      e.printStackTrace();
      throw new RuntimeException("cannot create scheduler");
    }
    LOGGER.info("success start schedule");
  }

  public void scheduleJob(JobDetail jobDetail, Trigger trigger) {
    try {
      Set<Trigger> triggerSet = new HashSet();
      triggerSet.add(trigger);
      myScheduler.scheduleJob(jobDetail, triggerSet, true);
      LOGGER.info("new job is reschedule {}", jobDetail.getKey().toString());
    } catch (SchedulerException e) {
      LOGGER.error("unexpected scheduler exception", e);
    }
  }

  public void scheduleJob(JobDetail jobDetail, Set<? extends Trigger> triggerSet) {
    try {
      myScheduler.scheduleJob(jobDetail, triggerSet, true);
      LOGGER.info("new job is reschedule {}", jobDetail.getKey().toString());
    } catch (SchedulerException e) {
      LOGGER.error("unexpected scheduler exception", e);
    }
  }
}
