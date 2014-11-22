import java.util.Random;
public class QuickSort{
	public static void main(String[] args){
		int[] array = {100, 334, 5, 234, 12, 712, 45, 11};
		quickSort(array, 0, array.length - 1);
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
	}

	public static void quickSort(int[] array, int start, int end){
		if(end > start){
			// i: end of the smaller list + 1
			int i = start;
			int temp;

			int pivot = array[end];
			int random = start + new Random().nextInt(end - start + 1);
			array[end] = array[random];
			array[random] = pivot;
			pivot = array[end];

			for(int j = start; j < end; j ++){
				if(array[j] < pivot){
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
					i ++;
				}
			}
			array[end] = array[i];
			array[i] = pivot;
			quickSort(array, start, i-1);
			quickSort(array, i+1, end);
		}
	}
}