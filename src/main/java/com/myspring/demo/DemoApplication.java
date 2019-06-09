package com.myspring.demo;

import com.myspring.demo.quartz.*;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;


@Controller
@SpringBootApplication
@ComponentScan(basePackages = {"com.myspring.demo.controller"})
public class DemoApplication {
    public static boolean  IS_punch=false;
    @RequestMapping("/")
    public String index() {
//        contolpunch();
        return "index";
    }

        public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private CronTrigger buildCronTrigger(String name,String group, String expression){
        CronTrigger startTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(expression)).build();
        return startTrigger;
    }


    public void contolpunch() {
        JobDetail startPunchDetail = JobBuilder.newJob(Startpunch.class)
                .withDescription("开始签到")
                .build();

        CronTrigger startTrigger = buildCronTrigger("startTrigger", "group", "\"0 7 0 ? * MON-FRI\"");


        JobDetail stopPunchDetail = JobBuilder.newJob(Endpunch.class)
                .withDescription("停止签到")
                .build();

        CronTrigger stopTrigger = buildCronTrigger("stopTrigger", "group1", "\"0 19 00 ? * MON-FRI\"");

        JobDetail collectPunchDetail = JobBuilder.newJob(Collectionpunch.class)
                .withDescription("开始收集签到情况")
                .build();

        CronTrigger collectionTrigger = buildCronTrigger("collectTrigger", "group2", "\"0 0 0 ? * MON-FRI\"");

        try {
            if (!GetSchedual.getScheduler().checkExists(startTrigger.getKey()))
                GetSchedual.getScheduler().scheduleJob(startPunchDetail, startTrigger);
            if (!GetSchedual.getScheduler().checkExists(stopTrigger.getKey()))
                GetSchedual.getScheduler().scheduleJob(stopPunchDetail, stopTrigger);
            if (!GetSchedual.getScheduler().checkExists(collectionTrigger.getKey()))
                GetSchedual.getScheduler().scheduleJob(collectPunchDetail, collectionTrigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
