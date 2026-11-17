class Solution {
    public int solution(int[] cookie) {
        int answer = 0;
        int n = cookie.length;
        
        for (int i = 0; i < n - 1; i++) {
            int l = i, r = i + 1;
            
            int lsum = cookie[l];
            int rsum = cookie[r];
            
            while (true) {
                if (lsum == rsum) answer = Math.max(answer, lsum);
                
                if (lsum >= rsum && r < n - 1) {
                    rsum += cookie[++r];
                }
                else if (lsum < rsum && l > 0) {
                    lsum += cookie[--l];
                }
                else break;
            }
        }
        
        
        return answer;
    }
}