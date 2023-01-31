package com.eazybytes.eazyschool.model;

import lombok.Data;

/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean.
* */
@Data
public class Holiday {
	
	private final String day;
	private final String reason;
	private final Type type;
	
	public enum Type {
		FESTIVAL, FEDERAL
	}

//	public Holiday(String day, String reason, Type type) {  //NO SON NECESARIOS CON LOMBOK DATA
//		super();
//		this.day = day;
//		this.reason = reason;
//		this.type = type;
//	}

//	public String getDay() {
//		return day;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public Type getType() {
//		return type;
//	}
//
//	@Override
//	public String toString() {
//		return "Holiday [day=" + day + ", reason=" + reason + ", type=" + type + "]";
//	}
//	
	

}
