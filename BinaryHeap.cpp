#include<bits/stdc++.h>
using namespace std;

void swap(int *x, int *y) 
{ 
    int temp = *x; 
    *x = *y; 
    *y = temp; 
} 
  
class MinHeap 
{ 
    int *harr;  
    int capacity;  
    int heap_size;

    public: 
    MinHeap(int capacity); 
  
    void MinHeapify(int i); 
  
    int getParent(int i) { return (i-1)/2; } 
    int getLeftChild(int i) { return (2*i + 1); } 
    int getRightChild(int i) { return (2*i + 2); } 
  
    int extractMin(); 

    void decreaseKey(int i, int new_val); 

    int getMin() { return harr[0]; } 

    void deleteKey(int i); 

    void insertKey(int k); 
}; 
   
MinHeap::MinHeap(int cap) 
{ 
    heap_size = 0; 
    capacity = cap; 
    harr = new int[cap]; 
} 
  

void MinHeap::insertKey(int k) 
{ 
    if (heap_size == capacity) 
    { 
        cout << "\nOverflow: Could not insert Key\n"; 
        return; 
    } 
    heap_size++; 
    int i = heap_size - 1; 
    harr[i] = k; 
  
    while (i != 0 && harr[getParent(i)] > harr[i]) 
    { 
       swap(&harr[i], &harr[getParent(i)]); 
       i = getParent(i); 
    } 
} 
  
void MinHeap::decreaseKey(int i, int new_val) 
{ 
    harr[i] = new_val; 
    while (i != 0 && harr[getParent(i)] > harr[i]) 
    { 
       swap(&harr[i], &harr[getParent(i)]); 
       i = getParent(i); 
    } 
} 
int MinHeap::extractMin() 
{ 
    if (heap_size <= 0) 
        return INT_MAX; 
    if (heap_size == 1) 
    { 
        heap_size--; 
        return harr[0]; 
    } 
  
    int root = harr[0]; 
    harr[0] = harr[heap_size-1]; 
    heap_size--; 
    MinHeapify(0); 
  
    return root; 
} 

void MinHeap::deleteKey(int i) 
{ 
    decreaseKey(i, INT_MIN); 
    extractMin(); 
} 
  
void MinHeap::MinHeapify(int i) 
{ 
    int l = getLeftChild(i); 
    int r = getRightChild(i); 
    int smallest = i; 
    if (l < heap_size && harr[l] < harr[i]) 
        smallest = l; 
    if (r < heap_size && harr[r] < harr[smallest]) 
        smallest = r; 
    if (smallest != i) 
    { 
        swap(&harr[i], &harr[smallest]); 
        MinHeapify(smallest); 
    } 
} 

int main(){
    int n;
    cin>>n;
    MinHeap *m = new MinHeap(n);
    for (int i = 0; i < n; i++)
    {
        int k;
        cin>>k;
        m->insertKey(k);
    }
}