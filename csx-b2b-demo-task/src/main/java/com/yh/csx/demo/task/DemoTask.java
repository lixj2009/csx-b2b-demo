package com.yh.csx.demo.task;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@JobHandler("DemoTask")
public class DemoTask extends IJobHandler {
	@Override
	public ReturnT<String> execute(String s) throws Exception {
		log.info("示例任务开始");

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			XxlJobLogger.log(e);
			e.printStackTrace();
		}
		XxlJobLogger.log("示例任务结束");
		log.info("示例任务结束");
		return ReturnT.SUCCESS;
	}
}
