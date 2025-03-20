package com.example.java_ai_function_callback;

public class WeatherResponse {

	private final double temp;

	private final double feelsLike;

	private final double tempMin;

	private final double tempMax;

	private final int pressure;

	private final int humidity;

	private final SpringAiJavaFunctionCallbackApplication.Unit unit;

	public WeatherResponse(double temp, double feelsLike, double tempMin,
			double tempMax, int pressure, int humidity, SpringAiJavaFunctionCallbackApplication.Unit unit) {
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pressure = pressure;
		this.humidity = humidity;
		this.unit = unit;
	}

	public double getTemp() {
		return temp;
	}

	public double getFeelsLike() {
		return feels_like;
	}

	public double getTempMin() {
		return temp_min;
	}

	public double getTempMax() {
		return temp_max;
	}

	public int getPressure() {
		return pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public SpringAiJavaFunctionCallbackApplication.Unit getUnit() {
		return unit;
	}

}
