import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        int answer = 0;

        char[][] arr1 = new char[N][M];
        char[][] arr2 = new char[N][M];

        for(int i = 0 ; i < N ; ++i){
            String str = br.readLine();
            arr1[i] = str.toCharArray();
        }

        for(int i = 0 ; i < N ; ++i){
            String str = br.readLine();
            arr2[i] = str.toCharArray();
        }

        for(int i = 0 ; i < N - 2 ; ++i){
            for(int j = 0 ; j < M - 2 ; ++j) {
                if(arr1[i][j] == arr2[i][j])
                    continue;

                answer++;
                for(int x = i; x < i + 3; ++x) {
                    for(int y = j; y < j + 3 ; ++y) {
                        arr1[x][y] = arr1[x][y] == '1' ? '0' : '1';
                    }
                }
            }
        }

        for(int i = 0 ; i < N ; ++i){
            for(int j = 0 ; j < M ; ++j){
                if(arr1[i][j] != arr2[i][j]){
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(answer);

    }
}