#include<bits/stdc++.h>
using namespace std;

void nCr(int a[],int n,int r, int idx)
{
    if (idx == r)
    {
        cout<<a[0];
        for (int i = 1; i < r; i++)
        {
            cout<<" "<<a[i];
        }
        cout<<endl;
        return;
    }
    
    for (int i = n-1-idx; i>=0 ; i--)
    {

        if(a[idx-1]>i){
        a[idx] = i;
        nCr(a,n,r,idx+1);
        }
    }
    
}
int main()
{
    int n,r;
    cin>>n>>r;

    int arr[r];
    nCr(arr,n,r,0);
}