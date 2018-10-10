package vn.thai.scheduler.service.impl;


import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Random;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.thai.scheduler.job.MyJobExcecuterService;
import vn.thai.scheduler.job.MySchedule;
import vn.thai.scheduler.service.MyJobRefreshingService;
import vn.thai.scheduler.service.LoggingTimeService;

@Service
public class MyJobRefreshingServiceImpl implements MyJobRefreshingService {

    @Autowired
    MySchedule myScheduleFactory;

    @Autowired
    LoggingTimeService schedulingService;

    @Scheduled(fixedRateString = "${jobs.update.rate}")
    public void updateJobs() {

        int jobId = randomInt(3);

        JobDetail job = newJob(MyJobExcecuterService.class)
            .withIdentity("myJob" + jobId, "group1")
            .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
            .withIdentity("myTrigger" + jobId, "group1")
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever())
            .build();

        myScheduleFactory.scheduleJob(job, trigger);
    }

    private int randomInt(int digits) {
        int minimum = (int) Math.pow(10, digits - 1); // minimum value with 2 digits is 10 (10^1)
        int maximum =
            (int) Math.pow(10, digits) - 1; // maximum value with 2 digits is 99 (10^2 - 1)
        Random random = new Random();
        return minimum + random.nextInt((maximum - minimum) + 1);

    }

}
