package com.fullstackboy.quartzdemo1.com.fullstackboy.quartzdemo1.controller;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 演示job
 */
public class DemoJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //打印当前的执行时间，格式为2017-01-01 00：00:00
        Date date = new Date();
        SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is :" + sf.format(date));
        //编写具体的业务逻辑
        System.out.println("Hello World!");
        System.out.println("*******************************");
    }
}
