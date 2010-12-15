package com.railinc.jook.interaction;
/**
 * This is the low level interation which is tab,popuptab,etc... and any URL associated with it.
 * @author sdtxs01
 *
 */
public interface JookInteraction {
	
	String getType();
	String getTitle();
	String getUrl();
}
