package com.railinc.common.hibernate;

import java.util.UUID;

public class UUIDTxRefProvider extends ThreadBasedTxRefProvider {
	public String createTxRef() {
		String tmp = UUID.randomUUID().toString();
		super.setTxRef(tmp);
		return tmp;
	}
}
