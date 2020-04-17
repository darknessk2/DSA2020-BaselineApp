package list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class SLinkedList<E> implements java.util.List<E>{
	private static enum MoveType {
		NEXT, PREV
	};

	private Node<E> head, tail;

	private int size;

	public SLinkedList() {
		head = new Node<>(null, null);
		tail = new Node<>(null, null);
		head.next = tail;
		tail.next = head;
		size = 0;
	}

	// Utilities (Private)
	private void checkValidIndex(int index) {
		if ((index < 0) || (index >= size)) {
			String message = String.format("Invalid index (=%d)", index);
			throw new IndexOutOfBoundsException(message);
		}
	}

	private Node<E> getDataNode(int index) {
		checkValidIndex(index);
		Node<E> curNode = head.next;
		int runIndex = 0;
		while (curNode != tail) {
			if (index == runIndex)
				break;
			runIndex += 1;
			curNode = curNode.next;
		}
		return curNode;
	}

	// getNode: can return head
	private Node<E> getNode(int index) {
		if ((index < -1) || (index >= size)) {
			String message = String.format("Invalid index (including head)%d)", index);
			throw new IndexOutOfBoundsException(message);
		}
		Node<E> curNode = head;
		int runIndex = -1;
		while (curNode != tail) {
			if (index == runIndex)
				break;
			runIndex += 1;
			curNode = curNode.next;
		}
		return curNode;
	}
	// Group-1: read list’s properties
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	// Group-2: add elements
	private void addAfter(Node<E> afterThis, Node<E> newNode) {
		newNode.next = afterThis.next;
		afterThis.next = newNode;
		if (newNode.next == tail)
			tail.next = newNode;
		size += 1;
	}

	public void add(int index, E element) {
		Node<E> prevNode = getNode(index - 1);
		Node<E> newNode = new Node<>(null, element);
		addAfter(prevNode, newNode);
	}

	public boolean add(E e) {
		Node<E> newNode = new Node(null, e);
		Node<E> lastNode = tail.next;
		addAfter(lastNode, newNode);
		return true;
	}

	// Group-3: remove elements
	private Node<E> removeAfter(Node<E> afterThis){
		Node<E> removedNode = afterThis.next;
		afterThis.next = removedNode.next;
		if (removedNode.next == tail)
			tail.next = afterThis;
		removedNode.next = null;
		this.size --; 
		//after remove, have to minus size by one, if not it would occur nullPointerException
		return removedNode;
	}

	public boolean remove(Object o) {
		Node<E> prevNode = head;
		Node<E> curNode = head.next;
		boolean found = false;
		while (curNode != tail) {
			if (curNode.element.equals(o)) {
				found = true;
				removeAfter(prevNode);
				break;
			}
			curNode = curNode.next;
			prevNode = prevNode.next;
		}
		return found;
	}

	public E remove(int index) {
	 if(size == 0){
	 String message = String.format("Remove at %d, but the list is empty", index);
	 throw new IndexOutOfBoundsException(message);
	 }
	 Node<E> prevNode = getNode(index-1);
	 Node<E> curNode = prevNode.next;
	 removeAfter(prevNode);
	 return curNode.element;
	 }

	public void clear() {
		/* here: code */ }

	// Group-4: set and get elements with indices
	public E get(int index) {
		/* here: code */ 
		checkValidIndex(index);
		//return this.elements[index];
		return this.getDataNode(index).element;}

	public E set(int index, E element) {
		/* here: code */ 
		checkValidIndex(index);
		E oldElement = this.getDataNode(index).element;
		this.getDataNode(index).element = element ;
		return oldElement;}

	// Grpup-5: map an object to its index + check object existing?
	public int indexOf(Object o) {
		/* here: code */ 
		int foundIdx = -1;
		for (int idx = 0; idx < this.size; idx++) {
			if (this.getDataNode(idx).equals(o)) { // == not
				foundIdx = idx;
				break;
			}
		}
		return foundIdx; }

	public int lastIndexOf(Object o) {
		/* here: code */ 
		int foundIdx = -1;
		for (int idx = this.size - 1; idx >= 0; idx--) {
			if (this.getDataNode(idx).equals(o)) {
				foundIdx = idx;
				break;
			}
		}
		return foundIdx; }

	public boolean contains(Object o) {
		/* here: code */ 
		boolean found = false;
		for (int idx = 0; idx < this.size; idx++) {
			if (this.getDataNode(idx).equals(o)) {
				found = true;
				break;
			}
		}
		return found;}

	// Group-6: travel on lists
	public Iterator<E> iterator() {
		/* here: code */
		return new MyIterator();}

	public ListIterator<E> listIterator() {
		/* here: code */ 
		return new MyListIterator();
		}

	public ListIterator<E> listIterator(int index) {
		/* here: code */
		return new MyListIterator(index);
		}

	// Supplementary functionalities
	public Object[] toArray() {
		/* here: code */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public <T> T[] toArray(T[] a) {
		/* here: code */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public boolean containsAll(Collection<?> c) {
		/* here: code */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public boolean addAll(Collection<? extends E> c) {
		/* here: code */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public boolean addAll(int index, Collection<? extends E> c) {
		/*
		 * here: de
		 */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public boolean removeAll(Collection<?> c) {
		/* here: code */ 
		throw new UnsupportedOperationException("Not supported yet.");}

	public boolean retainAll(Collection<?> c) {
		/* here: code */
		throw new UnsupportedOperationException("Not supported yet.");}

	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException("Not supported yet.");}

	// Inner Classes
	class Node<E> {
		E element;
		Node<E> next;

		Node(Node<E> next, E element) {
			this.next = next;
			this.element = element;
		}

		void update(Node<E> next, E element) {
			this.next = next;
			this.element = element;
		}
	}

	public class MyIterator implements Iterator<E> {
		int cursor = 0;
		MoveType moveType = MoveType.NEXT;
		boolean afterMove = false;

		@Override
		public boolean hasNext() {
			//return this.cursor != MyArrayList.this.size;
			return this.cursor != SLinkedList.this.size;
		}

		@Override
		/*
		 * Move cursor to next + return previous element
		 */
		public E next() {
			cursor += 1;
			moveType = MoveType.NEXT;
			afterMove = true;
			return SLinkedList.this.getDataNode(cursor - 1).element;
			
			
		}

		@Override
		public void remove() {
			if (!afterMove)
				return;
			//MyArrayList.this.remove(cursor - 1);
			SLinkedList.this.remove(cursor-1);
			cursor -= 1;
			afterMove = false;
		}
	}

	public class MyListIterator extends MyIterator implements ListIterator<E> {
		public MyListIterator() {
			cursor = 0;
		}
		public MyListIterator(int index) {
			cursor = index;
		}
		public boolean hasPrevious() {
			return this.cursor != 0;
		}
		public void remove() {
			if (!afterMove)
				return;
			if (moveType == MoveType.NEXT)
				super.remove();
			else {
				//MyArrayList.this.remove(cursor);
				SLinkedList.this.remove(cursor);
				afterMove = false;
			}
		}
		public E previous() {
			cursor -= 1;
			moveType = MoveType.PREV;
			afterMove = true;
//			return MyArrayList.this.elements[cursor];
			return SLinkedList.this.getDataNode(cursor).element;
		}

		public int nextIndex() {
			return cursor;
		}

		public int previousIndex() {
			return cursor - 1;
		}

		public void set(E e) {
			if (!afterMove) return;
			if (moveType == MoveType.NEXT)
				//MyArrayList.this.set(cursor - 1, e);
				SLinkedList.this.set(cursor -1, e);
			else
				//MyArrayList.this.set(cursor, e);
				SLinkedList.this.set(cursor, e);
		}

		public void add(E e) {
			if (!afterMove)
				return;
			if (moveType == MoveType.NEXT)
				//MyArrayList.this.add(cursor - 1, e);
				SLinkedList.this.add(cursor - 1, e);
			else
				//MyArrayList.this.add(cursor, e);
				SLinkedList.this.add(cursor,e);
			cursor += 1;
			afterMove = false;
		}
	}



}// End of SLinkedList



