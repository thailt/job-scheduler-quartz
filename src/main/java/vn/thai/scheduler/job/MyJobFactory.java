package vn.thai.scheduler.job;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.simpl.SimpleJobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.thai.scheduler.service.LoggingTimeService;

@Component
public class MyJobFactory extends SimpleJobFactory {

    @Autowired
    LoggingTimeService schedulingService;

    @Override
    public Job newJob(TriggerFiredBundle bundle, Scheduler Scheduler) throws SchedulerException {
        MyJob job = (MyJob) super.newJob(bundle, Scheduler);
        job.setService(schedulingService);
        return job;
    }
}
