/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.utils;

import java.io.Serializable;

public class TestJson implements Serializable {

	/** 
	 * serialVersionUID :  
	 */
	private static final long serialVersionUID = 5743135886729659911L;

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@Override
//	public String toString() {
//		return "{" + "id:\"" + id + "\"" + ", name:\"" + name + "\"" + "}";
//	}
}
