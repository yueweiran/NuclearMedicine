package cn.med.entity;

public class WeiLan {
	private String Longitude;
	private String Latitude;
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	@Override
	public String toString() {
		return "WeiLan [Longitude=" + Longitude + ", Latitude=" + Latitude + "]";
	}
	
	
}
