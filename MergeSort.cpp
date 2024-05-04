#include<bits/stdc++.h>
using namespace std;

void merger(int a[],int l,int m,int r)
{
    int leftarr[m-l+1];
    int rightarr[r-m];

    for (int p = 0; p < m-l+1; p++)
    {
        leftarr[p] = a[l+p];
    }
    for (int q = 0; q < r-m; q++)
    {
        rightarr[q] = a[m+1+q];
    }
    int i=0;
    int j=0;
    int combinedidx = l;

    while (i<m-l+1 && j<r-m)
    {
        if (leftarr[i]<=rightarr[j])
        {
            a[combinedidx] = leftarr[i];
            i++;
        }
        else
        {
            a[combinedidx] = rightarr[j];
            j++;
        }
        combinedidx++;
    }
    while(i<m-l+1)
    {
        a[combinedidx] = leftarr[i];
        combinedidx++; i++;
    }
    while (j<r-m)
    {
        a[combinedidx] = rightarr[j];
        combinedidx++;j++;
    }
    delete[] leftarr;
    delete[] rightarr;
    
}

void mergeSort(int a[],int low,int high)
{
    if (low>=high)
    {
        return;
    }
    int mid = low+(high-low)/2;

    mergeSort(a,low,mid);
    mergeSort(a,mid+1,high);

    merger(a,low,mid,high);
    
}

int main()
{
    int n;
    cin>>n;
    int array[n];
    for (int i = 0; i < n; i++)
    {
        cin>>array[i];
    }
    for (int i = 0; i < n; i++)
    {
        cout<<array[i]<<" ";
    }
    cout<<endl;
    mergeSort(array,0,n-1);
    for (int i = 0; i < n; i++)
    {
        cout<<array[i]<<" ";
    }
}