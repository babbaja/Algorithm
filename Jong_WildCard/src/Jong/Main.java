package Jong;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static StringBuilder sb = new StringBuilder();
    public static java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
    static String W;
    static String S;
    static int n;
    static int[][] check = new int [101][101];
    static ArrayList<String> ans;
    static ArrayList<String> samples;
    
    public static void main(String[] args)throws Exception {
    	 int C = Integer.parseInt(br.readLine());
    	 for(int i =0; i< C; i++) { 		 
    		 W = br.readLine();
    		 n = Integer.parseInt(br.readLine().trim());
    		 samples = new ArrayList<String>();
    		 ans = new ArrayList<String>();
    		 
    		 for(int j =0; j<n; j++) {
    			S = br.readLine().trim();
     			init();
     			if(wildcard(0, 0) == 1) ans.add(S);
    		}
    		 Collections.sort(ans);
        	 for(int k =0; k<ans.size(); k++) {
        		 System.out.println(ans.get(k));
        	 }
    	 }	
    }
    
    static int wildcard(int w, int s) {   	
    	if(check[w][s] != -1) return check[w][s];
    	
    	if (w < W.length() && s < S.length()) {
    		if (W.charAt(w) == S.charAt(s) || W.charAt(w) == '?') {
    			return check[w][s] = wildcard(w + 1, s + 1);
    		}
    	}
    	
    	if (w == W.length()) {
    		if (s == S.length()) {
    			return check[w][s] = 1;
    		}
    		else return check[w][s] = 0;
    	}
    	
    	if (W.charAt(w) == '*') {
    		if (wildcard(w, s + 1) == 1 || (s < S.length() && wildcard(w + 1, s) == 1)) {
    			return check[w][s] = 1;
    		}
    	}

		return check[w][s] = 0;
    }

    static void init() {
    	for(int i = 0; i < 101; i++) {
    		for(int j = 0; j<101; j++) {
    			check[i][j] = -1;
    		}
    	}
    }   
}

//*이 두개 인경우에 중복계산이 발생할 수 있어 메모이제이션 필요 