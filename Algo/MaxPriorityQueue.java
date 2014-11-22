public class MaxPriorityQueue{
	private int[] maxHeap = new int[50];
	private int size = 0;

	public MaxPriorityQueue(int[] array){
		MaxHeap.buildMaxHeap(array);
		size = array.length;
		for(int i = 0; i < size; i++)
			maxHeap[i] = array[i];
	}

	public int max(){
		if(size < 1){
			System.out.println("Heap underflow.");
			return -1;
		}
		return maxHeap[0];
	}

	public int extractMax(){
		if(size < 1){
			System.out.println("Heap underflow.");
			return -1;
		}
		int max = maxHeap[0];
		maxHeap[0] = maxHeap[size-1];
		maxHeap[size-1] = 0;
		size --;
		MaxHeap.maxHeaplify(maxHeap, 0, size);
		return max;
	}

	public void increaseKey(int index, int key){
		if(index >= size)
			System.out.println("Invalid index");
		else if(maxHeap[index] >= key)
			System.out.println("New key is not greater than current key.");
		else{
			maxHeap[index] = key;
			int parent = (index - 1)/2;
			int temp;
			while(parent >= 0  && maxHeap[index] > maxHeap[parent]){
				temp = maxHeap[index];
				maxHeap[index] = maxHeap[parent];
				maxHeap[parent] = temp;
				index = parent;
				parent = (index - 1)/2;
			}
		}
	}

	public void insert(int element){
		if(size > 50){
			System.out.println("Heap overflow");
		}else{
			size ++;
			maxHeap[size - 1] = Integer.MIN_VALUE;
			increaseKey(size - 1, element);
		}
	}

	public void delete(int index){
		if(index >= size || index < 0){
			System.out.println("Invalid index");
		}else{
			maxHeap[index] = maxHeap[size - 1];
			size --;
			MaxHeap.maxHeaplify(maxHeap, index, size);
		}
	}

	public void print(){
		for(int i = 0; i < size; i ++){
			System.out.print(maxHeap[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args){
		int array[] = {6,23,45,12,7,58,90,34,27,3};
		MaxPriorityQueue mpq = new MaxPriorityQueue(array);
		mpq.print();
		System.out.println(mpq.extractMax());
		mpq.print();
		mpq.insert(15);
		mpq.print();
		mpq.delete(4);
		mpq.print();
	}
}