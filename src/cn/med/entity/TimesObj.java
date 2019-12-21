package cn.med.entity;

public class TimesObj {
	private String worktime;
	private String dakatime;
	private String state;
	public String getWorktime() {
		return worktime;
	}
	public void setWorktime(String worktime) {
		this.worktime = worktime;
	}
	public String getDakatime() {
		return dakatime;
	}
	public void setDakatime(String dakatime) {
		this.dakatime = dakatime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "TimesObj [worktime=" + worktime + ", dakatime=" + dakatime + ", state=" + state + "]";
	}
		
	
}
