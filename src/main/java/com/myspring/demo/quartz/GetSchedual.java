package com.myspring.demo.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class GetSchedual {
    private static SchedulerFactory factory = new StdSchedulerFactory();

    public static Scheduler getScheduler() throws SchedulerException {
        Scheduler schedual = factory.getScheduler();
        return schedual;
    }
}
