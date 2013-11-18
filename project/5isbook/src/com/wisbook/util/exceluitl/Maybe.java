/*
 * @copyright
 */
package com.wisbook.util.exceluitl;

import java.io.Serializable;

/**
 *
 * @param <T> 
 * @author willing
 */
abstract public class Maybe<T> implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Maybe() {
	}
	final static private Nothing<?> nothing = new Nothing<Object>();

	static public <T> Just<T> just(T v) {
		return new Just<T>(v);
	}

	@SuppressWarnings("unchecked")
	static public <T> Nothing<T> nothing() {
		return (Nothing<T>) nothing;
	}

	public boolean isNothing() {
		return !hasValue();
	}

	public boolean hasValue() {
		return !isNothing();
	}

	abstract public T value();

	public Just<T> toJust() {
		if (this instanceof Just) {
			return (Just<T>) this;
		}
		throw new ClassCastException();
	}

	public Nothing<T> toNothing() {
		if (this instanceof Nothing) {
			return (Nothing<T>) this;
		}
		throw new ClassCastException();
	}

	@Override
	final public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || !(obj instanceof Maybe)) {
			return false;
		}
		Maybe<?> other = (Maybe<?>) obj;
		if (this.isNothing()) {
			return other.isNothing();
		}
		if (other.isNothing()) {
			return false;
		}
		T value = value();
		Object otherValue = other.value();
		return value == null ? otherValue == null : value.equals(otherValue);
	}

	@Override
	final public int hashCode() {
		if (isNothing()) {
			return 0;
		}
		return 37 * value().hashCode();
	}

	@Override
	protected Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	final static public class Just<T> extends Maybe<T> {

		private static final long serialVersionUID = 1L;
		final private T t;

		private Just(T t) {
			if (t == null) {
				throw new NullJustException();
			}
			this.t = t;
		}

		@Override
		public boolean isNothing() {
			return false;
		}

		@Override
		public T value() {
			return t;
		}

		@Override
		public String toString() {
			return "Just{" + "v=" + t + '}';
		}
	}

	final static public class Nothing<V> extends Maybe<V> {

		private static final long serialVersionUID = 1L;

		private Nothing() {
		}

		@Override
		public boolean isNothing() {
			return true;
		}

		@Override
		public V value() {
			throw new NothingExistsException();
		}

		@Override
		public String toString() {
			return "Nothing";
		}
	}

	final static public class NothingExistsException
			extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}

	final static public class NullJustException extends RuntimeException {

		private static final long serialVersionUID = 1L;
	}
}
