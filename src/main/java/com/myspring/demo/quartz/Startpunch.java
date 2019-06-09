package com.myspring.demo.quartz;

import com.myspring.demo.DemoApplication;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Startpunch implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("开始打卡！");
        DemoApplication.IS_punch=true;
    }
}
