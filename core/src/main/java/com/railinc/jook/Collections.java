package com.railinc.jook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Collections {
	public static <O,I> Map<O,Collection<I>> partition(Collection<I> coll, Predicate<I,O> pred) {
		Map<O,Collection<I>> ret = new HashMap<O, Collection<I>>();
		Iterator<I> i = coll.iterator();
		while (i.hasNext()) {
			I o = i.next();
			O key = pred.call(o);
			if (ret.containsKey(key)) {
				Collection<I> keyedCollection = (Collection<I>) ret.get(key);
				keyedCollection.add(o);
			} else {
				Collection<I> keyedCollection = new ArrayList<I>();
				keyedCollection.add(o);
				ret.put(key, keyedCollection);
			}
		}
		
		
		return ret;
	}

	public static <T> Collection<T> select(
			List<T> ts,
			Predicate<T,Boolean> predicate) {
		return selectAsList(ts,predicate);
		
	}
	public static <T> List<T> selectAsList(
			List<T> ts,
			Predicate<T,Boolean> predicate) {
		List<T> l = new ArrayList<T>();
		for (T t : ts) {
			Boolean call = predicate.call(t);
			if (Boolean.TRUE.equals(call)) {
				l.add(t);
			}
		}
		return l;
		
	}
	public static <T,O> List<O> map(
			List<T> ts,
			Predicate<T,O> predicate) {
		List<O> l = new ArrayList<O>();
		for (T t : ts) {
			O call = predicate.call(t);
			if (call != null) {
				l.add(call);
			}
		}
		return l;
		
	}
}
