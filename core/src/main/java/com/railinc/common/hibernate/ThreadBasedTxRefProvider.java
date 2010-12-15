package com.railinc.common.hibernate;

public class ThreadBasedTxRefProvider implements TxRefProvider {
	final ThreadLocal<String> txref = new ThreadLocal<String>();
	
	public void setTxRef(String actor) {
		txref.set(actor);
	}
	
	public void unsetTxRef() {
		txref.remove();
	}
	
	@Override 
	public String getTxRef() {
		String string = txref.get();
		return string;
	}

	
}
