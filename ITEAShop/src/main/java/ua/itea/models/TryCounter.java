package ua.itea.models;

public class TryCounter {
	private int tryCounter = 1;
	private long failTime = 0;
	private String message;
	private boolean showForm;
	private boolean showMessage;
	private boolean accessGranted;
	
	public TryCounter() {}

	public int getTryCounter() {
		return tryCounter;
	}

	public void setTryCounter(int tryCounter) {
		this.tryCounter = tryCounter;
	}

	public long getFailTime() {
		return failTime;
	}

	public void setFailTime(long failTime) {
		this.failTime = failTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

	public boolean isShowMessage() {
		return showMessage;
	}

	public void setShowMessage(boolean showMessage) {
		this.showMessage = showMessage;
	}

	public boolean isAccessGranted() {
		return accessGranted;
	}

	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}
	
	
}
