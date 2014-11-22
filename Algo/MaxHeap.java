public class MaxHeap{
	// public static void main(String[] args){
	// 	// int[] array = {27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9, 0};
	// 	int[] array = {10,9,11,8,7,13,6,5,12,4,3,2,10};
	// 	new Heap().heapSort(array);
	// 	for(int i = 0; i < array.length; i++){
	// 		System.out.print(array[i] + " ");
	// 	} 
	// }

	public static void maxHeaplify(int[] array, int index, int size){
		int left, right, temp;
		int big = index;
		while(true){
			left = 2 * index + 1;
			right = 2 * index + 2;
			if(left < size && array[left] > array[index])
				big = left;
			if(right < size && array[right] > array[big])
				big = right;
			if(big == index)
				break;
			else{
				temp = array[index];
				array[index] = array[big];
				array[big] = temp;
				index = big;
			}
		}
	}

	public static void buildMaxHeap(int[] array){
		int start = (array.length - 2)/2;
		for(int i = start; i >= 0; i--){
			maxHeaplify(array, i, array.length);
		}
	}

	public static void heapSort(int[] array){
		int length = array.length;
		int temp;
		buildMaxHeap(array);
		while(length>1){
			length --;
			temp = array[0];
			array[0] = array[length];
			array[length] = temp;
			maxHeaplify(array, 0, length);
		}
	}
}