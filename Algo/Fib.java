public class Fib{
	public static void main(String[] args){
		for(int i=1; i<10; i++)
			System.out.print(fib(i) + " ");
	}

	public static int fib(int n){
		if(n==0) return 0;
		if(n==1 || n==2) return 1;
		int[][] f = {{1,1},{1,0}};
		powMatrix(f, n-1);
		return f[0][0];
	}

	public static void powMatrix(int[][] f, int n){
		if(n<2) return;
		powMatrix(f, n/2);
		twoMatrix(f);
		if(n%2 == 1)
			oneMatrix(f);
	}

	public static void oneMatrix(int[][] f){
		int a = f[0][0];
		int b = f[0][1];
		f[0][0] = a+b;
		f[0][1] = f[1][0] = a;
		f[1][1] = b;
	}

	public static void twoMatrix(int[][] f){
		int a = f[0][0];
		int b = f[0][1];
		int c = f[1][0];
		int d = f[1][1];
		f[0][0] = a*a + b*c;
		f[0][1] = a*b + b*d;
		f[1][0] = c*a + d*c;
		f[1][1] = c*b + d*d;
	}
}