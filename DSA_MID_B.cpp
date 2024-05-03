#include <bits/stdc++.h>
using namespace std;

typedef long long ll;

struct node
{
    ll data;
    node *left;
    node *right;
    node *mid;
    node(ll x)
    {
        data = x;
        left = right = mid = NULL;
    }
};
node* root = NULL;
void insert_node(node *p, int op,int v,int k)
{
    
    if(!p) return;
    if (p->data == v)
    {
        if (op == 0)
            p->left = new node(k);
        if (op == 1)
            p->mid = new node(k);
        if (op == 2)
         p->right = new node(k);

         return;
    }
    
    
    insert_node(p->left,v,op,k);
    insert_node(p->mid,v,op,k);
    insert_node(p->right,v,op,k);

}

void print(node* p){
    if(!p) return;
    print(p->left);
    print(p->mid);
    cout<<p->data<<endl;
    print(p->right);
    
}
int main()
{
    int r,n;
    int op,key,val;
    scanf("%d ",&r);
    root = new node(r);
    node* roof = root;
    scanf("%d ", &n);
    while(n--)
    {
        scanf("%d %d %d ",&op,&val,&key);
        insert_node(root,op,val,key);
    }
    
    print(roof);
}
