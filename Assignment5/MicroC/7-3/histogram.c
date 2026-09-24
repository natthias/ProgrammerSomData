void main() {
  int arr[7];
  arr[0] = 1;
  arr[1] = 2;
  arr[2] = 1;
  arr[3] = 1;
  arr[4] = 1;
  arr[5] = 2;
  arr[6] = 0;

  int sump;
  sump = 0;

  int freq[4];
  freq[0] = 0;
  freq[1] = 0;
  freq[2] = 0;
  freq[3] = 0;

  histogram(7 , arr , 3, freq);
  
  printarr(4, freq);
  println;
}

void histogram(int n, int ns[], int max, int freq[]) {
    int i;
    for(i = 0; i < n ; i = i + 1){
        freq[ns[i]] = freq[ns[i]]+1;
    }
}

void printarr(int len, int a[]) {
  int i; 
    for(i = 0; i < len ; i = i + 1){
    print a[i]; 
  } 
}
