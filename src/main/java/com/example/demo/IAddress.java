package com.example.demo;

import java.util.Date;

public interface IAddress {
	public IAddress getPrevious();
	public void setPrevious(IAddress previous);
	public String getName();
	public Date getTs();
}
