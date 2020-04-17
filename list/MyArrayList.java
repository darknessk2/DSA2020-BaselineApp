package list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import geom.Point2D;

public class MyArrayList<E> implements java.util.List<E>{
private static enum MoveType{ NEXT, PREV};
private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

//Internal data fields
private E[] elements;
private int size;
//int size;
	//Constructor
	public MyArrayList(int capacity) throws IllegalArgumentException {
		if ((capacity < 0) || (capacity > MAX_CAPACITY)) {
			String message = String.format("Invalid capacity (=%d)", capacity);
			throw new IllegalArgumentException(message);
		}
		this.elements = (E[]) new Object[capacity];
		this.size = 0;
	}

	public MyArrayList() throws IllegalArgumentException {
		this(10);
	}

	// Utitilies
	private void checkValidIndex(int index, int min, int max) {
		if ((index < min) || (index > max)) {
			String message = String.format("Invalid index (=%d)", index);
			throw new IndexOutOfBoundsException(message);
		}
	}

	private void checkCapacity(int minCapacity) {
		if ((minCapacity < 0) || (minCapacity > MAX_CAPACITY))
			throw new OutOfMemoryError("Not enough memory to store the array");
		if (minCapacity < this.elements.length)
			return;
		else {
			// grow
			int oldCapacity = this.elements.length;
			int newCapacity = oldCapacity + (oldCapacity >> 1);
			if (newCapacity < 0)
				newCapacity = MAX_CAPACITY;
			this.elements = Arrays.copyOf(this.elements, newCapacity);
		}
	}

 //Group-1: read list’s properties
 public int size() { return this.size; }
 public boolean isEmpty() { return this.size == 0; }

 //Group-2: add elements
	public void add(int index, E element) {
		checkValidIndex(index, 0, size);
		if (element == null)
			throw new NullPointerException("Can not add null pointer");
		checkCapacity(this.size + 1);

		int copyCount = (this.size - 1) - index + 1;
		System.arraycopy(this.elements, index, this.elements, index + 1, copyCount);
		this.elements[index] = element;
		this.size++;
	}

	public boolean add(E e) {
		if (e == null)
			throw new NullPointerException("Can not add nullpointer");
		checkCapacity(this.size + 1);

		this.elements[this.size++] = e;
		return true;
	}

 //Group-3: remove elements
	public E remove(int index) {
		System.out.println("This.remove");
		checkValidIndex(index, 0, size - 1);
		E oldElement = this.elements[index];
		int copyCount = (this.size - 1) - (index + 1) + 1;
		System.arraycopy(this.elements, index + 1, this.elements, index, copyCount);
		this.size--;
		return oldElement;
	}

	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index < 0)
			return false;
		remove(index);
		return true;
	}

 public void clear(){/*here: code*/ }

 //Group-4: set and get elements with indices
	public E get(int index) {
		checkValidIndex(index, 0, size - 1);
		return this.elements[index];
	}

	public E set(int index, E element) {
		checkValidIndex(index, 0, size - 1);
		E oldElement = this.elements[index];
		this.elements[index] = element;
		return oldElement;
	}

// Group-5: map an object to its index + check object existing?

	public int indexOf(Object o) {
		int foundIdx = -1;
		for (int idx = 0; idx < this.size; idx++) {
			if (this.elements[idx].equals(o)) { // == not
				foundIdx = idx;
				break;
			}
		}
		return foundIdx;
	}

	public int lastIndexOf(Object o) {
		int foundIdx = -1;
		for (int idx = this.size - 1; idx >= 0; idx--) {
			if (this.elements[idx].equals(o)) {
				foundIdx = idx;
				break;
			}
		}
		return foundIdx;
	}

	public boolean contains(Object o) {
		boolean found = false;
		for (int idx = 0; idx < this.size; idx++) {
			if (this.elements[idx].equals(o)) {
				found = true;
				break;
			}
		}
		return found;
	}

 //Group-6: travel on lists
 public Iterator<E> iterator(){
	//return null; 
//	return new ListIterator<E>(this);
	return new MyIterator();
	 }
 public ListIterator<E> listIterator(){
	//return null;/*here: code*/ 
	 return new MyListIterator();
	 }
 public ListIterator<E> listIterator(int index){
	//return null;/*here: code*/ 
	 return new MyListIterator(index);
	 }

 //Supplementary functionalities
 public Object[] toArray() {
	//DIY
	 return elements;/*here: code*/ }

	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

 public List<E> subList(int fromIndex, int toIndex) {
	 int length = toIndex - fromIndex +1;
	 List<E> sublist = new ArrayList<>();
	for(int i = 0; i< length; i++) {
		sublist.add(this.get(fromIndex+i));
	}
	 return sublist;
 }

 //Inner Classes
	public class MyIterator implements Iterator<E> {
		int cursor = 0;
		MoveType moveType = MoveType.NEXT;
		boolean afterMove = false;

		@Override
		public boolean hasNext() {
			return this.cursor != MyArrayList.this.size;
		}

		@Override
		/*
		 * Move cursor to next + return previous element
		 */
		public E next() {
			cursor += 1;
			moveType = MoveType.NEXT;
			afterMove = true;
			return MyArrayList.this.elements[cursor - 1];
		}

		@Override
		public void remove() {
			if (!afterMove) {System.out.println("afterMove:"+afterMove);
				return;}
			MyArrayList.this.remove(cursor - 1);
			cursor -= 1;
			afterMove = false;
		}
	}
 
	public class MyListIterator extends MyIterator implements ListIterator<E> {
	//DIY
		public MyListIterator() {
			cursor = 0;
		}//
		public MyListIterator(int index) {
			cursor = index;
		}

		public boolean hasPrevious() {
			return this.cursor != 0;
		}

		public int previousIndex() {
			return cursor - 1;
		}

		public E previous() {
			cursor -= 1;
			moveType = MoveType.PREV;
			afterMove = true;
			return MyArrayList.this.elements[cursor];
		}

		public int nextIndex() {
			return cursor;
		}

		public void add(E e) {
			if (!afterMove)
				return;
			if (moveType == MoveType.NEXT)
				MyArrayList.this.add(cursor - 1, e);
			else
				MyArrayList.this.add(cursor, e);
			cursor += 1;
			afterMove = false;
		}

		public void remove() {
			if (!afterMove)
				return;
			if (moveType == MoveType.NEXT)
				super.remove();
			else {
				MyArrayList.this.remove(cursor);
				afterMove = false;
			}
		}

		public void set(E e) {
			if (!afterMove)
				return;
			if (moveType == MoveType.NEXT)
				MyArrayList.this.set(cursor - 1, e);
			else
				MyArrayList.this.set(cursor, e);
		}

	}
 }//End of MyArrayList

