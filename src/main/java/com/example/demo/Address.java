package com.example.demo;

import java.util.Date;

public class Address implements IAddress{

	public String name;
	
	public IAddress previous;
	
	public Date ts = new Date();

	public Address(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IAddress getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(IAddress previous) {
		this.previous = previous;
	}

	@Override
	public Date getTs() {
		return ts;
	}
	
	public static void main(String[] args) {
		System.out.println(new Address("test"));
		System.out.println(new Address("test"));
	}
}
