package list;

interface Iterator<E> {
	public boolean hasNext();

	public E next();

	public void remove(); // optional
}
interface ListIterator<E> {
//for moving forward: head to tail
	public boolean hasNext();

	public E next();

	public int nextIndex();

	// for moving backward: tail back to head
	public boolean hasPrevious();

	public E previous();

	public int previousIndex();

	// for processing lists during a travelling
	public void remove();

	public void set(E e);

	public void add(E e);
}

