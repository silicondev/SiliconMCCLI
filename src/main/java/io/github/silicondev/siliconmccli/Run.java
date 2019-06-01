package io.github.silicondev.siliconmccli;

public class Run {
	private int id;
	private Runnable code;
	
	public Run(int id, Runnable code) {
		this.id = id;
		this.code = code;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void runCode() {
		Thread newThread = new Thread(code);
		newThread.start();
	}
}
