package com.nl.trace;

public class TestDataSeeder {

	public TestDataSeeder() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.name"));
		System.getProperties().entrySet().forEach(entry -> System.out.println(entry.getKey() +"="+ entry.getValue()));
	}
}
