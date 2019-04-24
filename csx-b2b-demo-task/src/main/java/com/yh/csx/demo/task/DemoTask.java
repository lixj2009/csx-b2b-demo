package com.yh.csx.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DemoTask {

	@Scheduled(cron = "0 */1 * * * ?")
	public void demoTask() {
		log.info("示例任务开始");

		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("示例任务结束");
	}
}
