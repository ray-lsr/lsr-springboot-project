package cn.lsr.discovery.core.thread;

import cn.lsr.common.log.LogUtil;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 轮询线程基类
 * @Author: lsr
 * @Date 2022年08月06日 22:56
 */
public abstract class AbstractPollThread {

    private Logger log = LogUtil.getSysLog(AbstractPollThread.class);

    private long delay = 60; // 延期时间(第一次执行时间-调用时间)
    private long pollInterval = 60; //轮询时间间隔(s)
    private ScheduledExecutorService scheduledExecutorService;
    private String threadName;
    private boolean isTimerStart = false; //定时器是否启动
    private boolean isOnProcessing = false; //是否在执行处理

    abstract void process();

    public AbstractPollThread(String threadName,Long delay,Long pollInterval){
        this.threadName = threadName;
        if (pollInterval != null && pollInterval > 0) {
            this.pollInterval = pollInterval;
        }
        if (delay != null) {
            this.delay = delay;
        }
    }

    public void start() {

        scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new CustomizableThreadFactory("timer-"+this.threadName));
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                process();
            } catch (Exception exception) {

            }finally {

            }
        }, delay, pollInterval, TimeUnit.SECONDS);
    }

    public void shuntDown() {
        if (null != scheduledExecutorService) {
            scheduledExecutorService.shutdown();
        }
    }


}
