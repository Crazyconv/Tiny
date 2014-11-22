// Longest Common Subsequence of two strings
public class LCS{
	public static void main(String[] args){
		// String s1 = "nematode_knowledge";
		// String s2 = "empty_bottle";
		String s1 = "ABCDEFGH";
		String s2 = "ABCGIJKDC";

		// method 1
		int[][] lcs = lengthLCS(s1, s2);
		System.out.println(lcs[s1.length()][s2.length()]);
		String cs = extractLCS(s1, s2, lcs);
		System.out.println(cs);

		// method 2
		String[][] array = new String[s1.length()+1][s2.length()+1];
		for(int i = 0; i <= s1.length(); i++){
			for(int j = 0; j <= s2.length(); j++){
				array[i][j] = "";
			}
		}
		String cs2 = LCS2(s1, s2, array, s1.length()-1, s2.length()-1);
		System.out.println(cs2);
	}

	public static int[][] lengthLCS(String s1, String s2){
		int[][] array = new int[s1.length()+1][s2.length()+1];
		for(int i = 0; i <= s1.length(); i++)
			array[i][0] = 0;
		for(int j = 1; j <= s2.length(); j++)
			array[0][j] = 0;
		for(int i = 1; i <= s1.length(); i++){
			for(int j = 1; j <= s2.length(); j++){
				if(s1.charAt(i-1) == s2.charAt(j-1))
					array[i][j] = array[i-1][j-1] + 1;
				else if(array[i-1][j] > array[i][j-1])
					array[i][j] = array[i-1][j];
				else
					array[i][j] = array[i][j-1];			
			}
		}
		return array;
	}

	public static String extractLCS(String s1, String s2, int[][] lcs){
		int i = lcs.length-1;
		int j = lcs[0].length-1;
		int length = lcs[s1.length()][s2.length()];
		char[] chars = new char[length];
		int index = length - 1;
		while(i > 0 && j > 0 && index >= 0){
			if(s1.charAt(i-1) == s2.charAt(j-1)){
				chars[index] = s1.charAt(i-1);
				index --;
				i --;
				j --;
			}else if(lcs[i-1][j] > lcs[i][j-1]){
				i --;
			}else{
				j --;
			}
		}
		return new String(chars, 0, length);
	}

	public static String LCS2(String s1, String s2, String[][] LCSArray, int index1, int index2){
		if(index1 == -1 || index2 == -1) return "";
		if(s1.charAt(index1) == s2.charAt(index2)){
			if(LCSArray[index1][index2]=="")
				LCSArray[index1][index2] =  LCS2(s1, s2, LCSArray, index1-1, index2-1);
			LCSArray[index1+1][index2+1] = LCSArray[index1][index2] + s1.charAt(index1);
		}else{
			if(LCSArray[index1+1][index2]=="")
				LCSArray[index1+1][index2] = LCS2(s1, s2, LCSArray, index1, index2-1);
			if(LCSArray[index1][index2+1]=="")
				LCSArray[index1][index2+1] = LCS2(s1, s2, LCSArray, index1-1, index2);
			if(LCSArray[index1+1][index2].length() > LCSArray[index1][index2+1].length())
				LCSArray[index1+1][index2+1] = LCSArray[index1+1][index2];
			else
				LCSArray[index1+1][index2+1] = LCSArray[index1][index2+1];
		}
		return LCSArray[index1+1][index2+1];
	}
}