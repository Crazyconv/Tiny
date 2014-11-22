// chained matrix multiplication
public class CMM{
	public static void main(String[] args){
		int[] dimension = {30,1,40,10,25};

		// method 1
		int min = CMMNo(dimension);
		System.out.println(min);

		// method 2
		int[][] array = new int[dimension.length-1][dimension.length-1];
		String[][] sequence = new String[dimension.length-1][dimension.length-1];
		for(int i = 0; i < dimension.length-1; i++){
			array[i][i] = 0;
			sequence[i][i] = "" + i;
		}
		for(int i = 0; i < dimension.length-1; i++){
			for(int j = i+1; j < dimension.length-1; j++){
				array[i][j] = Integer.MAX_VALUE;
				sequence[i][j] = "";
			}
		}
		int min2 = CMM2(dimension, array, sequence, 0, dimension.length-1-1);
		System.out.println(min2);
		System.out.println(sequence[0][dimension.length-1-1]);
	}

	public static int CMMNo(int[] dimension){
		int size = dimension.length - 1;
		int[][] array = new int[size][size];
		String[][] sequence = new String[size][size];
		int min;
		int temp;
		for(int i = 0; i < size; i++){
			array[i][i] = 0;
			sequence[i][i] = "" + i;
		}
		for(int i = 0; i < size; i++){
			for(int j = i+1; j < size; j++){
				array[i][j] = Integer.MAX_VALUE;
				sequence[i][j] = "";
			}
		}
		for(int i = 1; i < size; i++){
			for(int j = 0; j+i < size; j++){
				for(int k = j; k < j+i; k++){
					temp = array[j][k] + array[k+1][j+i] + dimension[j] * dimension[k+1] * dimension[j+i+1];
					if (array[j][j+i] > temp){
						array[j][j+i] = temp;
						sequence[j][j+i] = "(" + sequence[j][k] + ")" + "(" + sequence[k+1][j+i] + ")";
					} 
				}
			}
		}
		// for(int i = 0; i < size; i++){
		// 	for(int j = 0; j < size; j++)
		// 		System.out.print(array[i][j] + " ");
		// 	System.out.println();
		// }
		System.out.println(sequence[0][size-1]);
		return array[0][size-1];
	}

	public static int CMM2(int[] dimension, int[][] array, String[][] sequence, int start, int end){
		if (start >= end) return 0;
		int temp;
		for(int i = start; i < end; i++){
			if(array[start][i] == Integer.MAX_VALUE)
				array[start][i] = CMM2(dimension, array, sequence, start, i);
			if(array[i+1][end] == Integer.MAX_VALUE)
				array[i+1][end] = CMM2(dimension, array, sequence, i+1, end);
			temp = array[start][i] + array[i+1][end] + dimension[start]*dimension[i+1]*dimension[end+1];
			if (array[start][end] > temp){
				array[start][end] = temp;
				sequence[start][end] = "(" + sequence[start][i] + ")" + "(" + sequence[i+1][end] + ")";
			}
		}
		return array[start][end];
	}
}