package org.lazan.t5.stitch.demo.pages;

import org.lazan.t5.stitch.model.ProgressTask;

public class ProgressLinkDemo {
	public ProgressTask getTask() {
		return new TestTask();
	}
	
	public static class TestTask implements ProgressTask {
		private float progress = 0;
		public void run() {
			int num = 100;
			for (int i = 0; i < num; ++ i) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
				progress = i * 1f / num;
			}
		}
		
		public float getProgress() {
			return progress;
		}
	}
}
