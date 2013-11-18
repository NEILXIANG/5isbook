package com.wisbook.util.exceluitl;

import java.io.Serializable; 
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @param <K> 
 * @param <V> 
 * @author willing
 */
public class ArrayMap<K, V> extends AbstractMap<K, V>
		implements Map<K, V>, Cloneable, Serializable {

	final static private long serialVersionUID = 1L;
	final static private int STEP = 2;
	//
	final static private int INIT_SIZE = 5;
	//
	private Object[] bucket;
	private int size;

	public ArrayMap() {
		init();
	}

	private void init() {
		bucket = new Object[INIT_SIZE << 1];
		size = 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (key == null) {
			return false;
		}
		for (int i = 0; i < bucket.length; i += STEP) {
			if (key.equals(bucket[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for (int i = 1; i < bucket.length; i += STEP) {
			Object v = bucket[i];
			if ((value == null ? v == null : value.equals(v))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		if (key == null) {
			return null;
		}
		for (int i = 0; i < bucket.length; i += STEP) {
			if (key.equals(bucket[i])) {
				return getValueByKeyIndex(i);
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		if (key == null) {
			throw new NullPointerException("the key could not be null");
		}
		// if the bucket already contains the key
		for (int i = 0; i < bucket.length; i += STEP) {
			if (key.equals(bucket[i])) {
				return updateToKeyIndex(i, key, value);
			}
		}
		// if the bucket is full
		if (size << 1 == bucket.length) {
			int i = bucket.length;
			bucket = enlargeBucket(bucket);
			addToKeyIndex(i, key, value);
			return null;
		}
		// find a empty slot and put
		for (int i = 0; i < bucket.length; i += STEP) {
			if (bucket[i] == null) {
				addToKeyIndex(i, key, value);
				return null;
			}
		}
		throw new RuntimeException(
				"impossible statement, may be bug or concurrent access.");
	}

	private Object[] enlargeBucket(Object[] bucket) {
		int cap = bucket.length >> 1;
		int newCap = cap * 3 / 2 + 1;
		Object[] newBucket = new Object[newCap << 1];
		System.arraycopy(bucket, 0, newBucket, 0, bucket.length);
		return newBucket;
	}

	@Override
	public V remove(Object key) {
		if (key == null) {
			return null;
		}
		for (int i = 0; i < bucket.length; i += STEP) {
			if (key.equals(bucket[i])) {
				return removeByKeyIndex(i);
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
			this.put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void clear() {
		init();
	}

	private V updateToKeyIndex(int index, K key, V value) {
		V old = getValueByKeyIndex(index);
		bucket[index] = key;
		bucket[index + 1] = value;
		return old;
	}

	private void addToKeyIndex(int index, K key, V value) {
		bucket[index] = key;
		bucket[index + 1] = value;
		size++;
	}

	private V removeByKeyIndex(int index) {
		@SuppressWarnings("unchecked")
		V v = (V) bucket[index + 1];
		bucket[index] = null;
		bucket[index + 1] = null;
		size--;
		return v;
	}

	private K getKeyByKeyIndex(int index) {
		@SuppressWarnings("unchecked")
		K k = (K) bucket[index];
		return k;
	}

	private V getValueByKeyIndex(int index) {
		@SuppressWarnings("unchecked")
		V v = (V) bucket[index + 1];
		return v;
	}

	@Override
	public Set<K> keySet() {
		return new KeySet();
	}

	private class KeySet extends AbstractSet<K> {

		@Override
		public Iterator<K> iterator() {
			return new KeyIterator();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains(Object o) {
			return containsKey(o);
		}

		@Override
		public boolean remove(Object o) {
			return ArrayMap.this.remove(o) != null;
		}

		@Override
		public void clear() {
			ArrayMap.this.clear();
		}
	}

	private class KeyIterator implements Iterator<K> {

		KeyIndexIterator keyIndexIterator = new KeyIndexIterator();

		@Override
		public boolean hasNext() {
			return keyIndexIterator.hasNext();
		}

		@Override
		public K next() {
			int index = keyIndexIterator.next();
			if (index == bucket.length) {
				return null;
			}
			return getKeyByKeyIndex(index);
		}

		@Override
		public void remove() {
			keyIndexIterator.removeCurrent();
		}
	}

	@Override
	public Collection<V> values() {
		return new ValueCollection();
	}

	private class ValueCollection extends AbstractCollection<V> {

		@Override
		public Iterator<V> iterator() {
			return new ValueIterator();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains(Object o) {
			return ArrayMap.this.containsValue(o);
		}

		@Override
		public void clear() {
			ArrayMap.this.clear();
		}
	}

	private class ValueIterator implements Iterator<V> {

		KeyIndexIterator keyIndexIterator = new KeyIndexIterator();

		@Override
		public boolean hasNext() {
			return keyIndexIterator.hasNext();
		}

		@Override
		public V next() {
			int keyIndex = keyIndexIterator.next();
			if (keyIndex == bucket.length) {
				return null;
			}
			return getValueByKeyIndex(keyIndex);
		}

		@Override
		public void remove() {
			keyIndexIterator.removeCurrent();
		}
	}

	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		return new EntrySet();
	}

	private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

		@Override
		public Iterator<Map.Entry<K, V>> iterator() {
			return new EntryIterator();
		}

		@Override
		public int size() {
			return size;
		}

		@Override
		public boolean contains(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> e = (Map.Entry<K, V>) o;
			K key = e.getKey();
			if (key == null) {
				return false;
			}
			V candidate = get(key);
			return candidate == null
					? e.getValue() == null : candidate.equals(e.getValue());
		}

		@Override
		public boolean remove(Object o) {
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			@SuppressWarnings("unchecked")
			Map.Entry<K, V> e = (Map.Entry<K, V>) o;
			return ArrayMap.this.remove(e.getKey()) != null;
		}

		@Override
		public void clear() {
			ArrayMap.this.clear();
		}
	}

	private class EntryIterator implements Iterator<Map.Entry<K, V>> {

		KeyIndexIterator keyIndexIterator = new KeyIndexIterator();

		@Override
		public boolean hasNext() {
			return keyIndexIterator.hasNext();
		}

		@Override
		public Map.Entry<K, V> next() {
			int keyIndex = keyIndexIterator.next();
			if (keyIndex == bucket.length) {
				return null;
			}
			return new Entry(keyIndex);
		}

		@Override
		public void remove() {
			keyIndexIterator.removeCurrent();
		}
	}

	private class Entry implements Map.Entry<K, V> {

		private int index;

		private Entry(int index) {
			this.index = index;
		}

		@Override
		public K getKey() {
			ensureState();
			return getKeyByKeyIndex(index);
		}

		@Override
		public V getValue() {
			ensureState();
			return getValueByKeyIndex(index);
		}

		@Override
		public V setValue(V value) {
			ensureState();
			V old = getValueByKeyIndex(index);
			bucket[index + 1] = value;
			return old;
		}

		@Override
		public boolean equals(Object o) {
			ensureState();
			if (!(o instanceof Map.Entry)) {
				return false;
			}
			Map.Entry<?, ?> e = (Map.Entry<?,?>) o;
			Object k1 = getKey();
			Object k2 = e.getKey();
			if (k1 == k2 || k1.equals(k2)) {
				Object v1 = getValue();
				Object v2 = e.getValue();
				if (v1 == v2 || (v1 != null && v1.equals(v2))) {
					return true;
				}
			}
			return false;
		}

		@Override
		public int hashCode() {
			ensureState();
			K key = getKey();
			V value = getValue();
			return key.hashCode() ^ (value == null ? 0 : value.hashCode());
		}

		private void ensureState() {
			if (bucket[index] == null) {
				throw new IllegalStateException(
						"this entry has already been removed");
			}
		}
	}

	private class KeyIndexIterator {

		private int index = -STEP;
		private int nextIndex = -STEP;

		public boolean hasNext() {
			findNext();
			return nextIndex != bucket.length;
		}

		private void findNext() {
			if (nextIndex == bucket.length) {
				return;
			}
			if (nextIndex != index) {
				return;
			}
			for (int i = index + STEP; i < bucket.length; i += STEP) {
				if (bucket[i] != null) {
					nextIndex = i;
					return;
				}
			}
			nextIndex = bucket.length;
			return;
		}

		public int next() {
			findNext();
			index = nextIndex;
			return index;
		}

		public void removeCurrent() {
			if (index == -STEP || index == bucket.length) {
				throw new IllegalStateException("no current key");
			}
			removeByKeyIndex(index);
		}
	}
}
