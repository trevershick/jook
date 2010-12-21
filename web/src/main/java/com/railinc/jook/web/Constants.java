package com.railinc.jook.web;

public interface Constants {
	String JOOK_PARAM_APP = "app";
	String CONTENT_TYPE_JAVASCRIPT = "text/javascript";
	String HTTP_HEADER_CACHE_CONTROL = "Cache-Control";

	String PROPKEY_CACHE_INTERACTIONS_MAXAGE_NOSHAKE = "cache.interactions.maxage.noshake";
	String PROPKEY_CACHE_INTERACTIONS_MAXAGE_SHAKE = "cache.interactions.maxage.shake";
	String PROPKEY_CACHE_JOOKDEPS_MAXAGE = "cache.jookdeps.maxage";
	String PROPKEY_CACHE_JOOKRESOURCES_MAXAGE = "cache.jookresources.maxage";
	String PROPKEY_CACHE_JOOKSCRIPT_MAXAGE = "cache.jookscript.maxage";
	String PROPKEY_CACHE_JOOKSTYLE_MAXAGE = "cache.jookstyle.maxage";

	int DEFAULT_CACHE_INTERACTIONS_MAXAGE_NOSHAKE = 0;
	int DEFAULT_CACHE_INTERACTIONS_MAXAGE_SHAKE = 0;
	int DEFAULT_CACHE_JOOKDEPS_MAXAGE = 0;
	int DEFAULT_CACHE_JOOKRESOURCES_MAXAGE = 0;
	int DEFAULT_CACHE_JOOKSCRIPT_MAXAGE = 0;
	int DEFAULT_CACHE_JOOKSTYLE_MAXAGE = 0;
}
