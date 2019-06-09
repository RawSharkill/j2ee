package com.myspring.demo.quartz;

import com.myspring.demo.DemoApplication;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Endpunch implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("关闭打卡系统！");
        DemoApplication.IS_punch=false;
    }
}
