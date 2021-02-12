
public interface MyQueue<T> extends Iterable<T>{
	
	/**kuruktaki toplam eleman sayisi*/
	int size();
	
	/** kuyruk boþ mu? */
	boolean isEmpty();
	
	/**kuyrugun sonuna eleman ekler*/
	boolean enqueue(T item);
	
	/** kuyrugun basindan eleman cikarir*/
	T dequeuNext();
	/** tekerleme metnini kullanarak bir sonraki elemani secer*/
	T dequeuWithCounting(String tekerleme);
}