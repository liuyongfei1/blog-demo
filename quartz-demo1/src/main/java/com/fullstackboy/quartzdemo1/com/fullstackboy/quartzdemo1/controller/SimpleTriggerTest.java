package com.fullstackboy.quartzdemo1.com.fullstackboy.quartzdemo1.controller;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO Liuyongfei
 *
 * @Author: Liuyongfei
 * @Date: 2020/7/21 13:22
 */
public class SimpleTriggerTest {
    public static void main(String[] args) throws SchedulerException {
        // 创建一个JobDetail示例，将该示例与HelloJob Class绑定
        JobDetail jobDetail = JobBuilder.newJob(DemoJob.class).withIdentity("myJob", "group1").build();

        // 创建一个Trigger实例，定义该job立即执行，并且每隔两秒钟重复执行一次，直到永远
        Trigger triger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2)
                                .repeatForever())
                .build();

        // 创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        Date date = new Date();
        SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is :" + sf.format(date));
        scheduler.scheduleJob(jobDetail, triger);

        // 立即执行
        // SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger演示", new Date());

        // 当前立即执行，指定重复次数3次，间隔500毫秒
        // 总执行次数为4次（首次执行+3次重复执行）
        // SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger演示", 3, 500);

        // 当前时间2秒后执行，当前时间5秒后结束，指定重复次数20次，间隔1000毫秒
        // 这里虽然指定执行次数为20次，但是实际只能执行3次（首次执行+2次重复执行），因为有"当前时间5秒后结束"这个因素在
        // SimpleTrigger simpleTrigger = new SimpleTrigger("simpleTrigger演示", new Date(System.currentTimeMillis() + 2000), new Date(System.currentTimeMillis() + 5000), 20, 1000);

        // TODO
        // 只要明白上面几种创建方式   其他没有写出的够着就很容易明白

    }
}
