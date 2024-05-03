#include <iostream>
using namespace std;

#define INT_MAX 2147483647

struct BST
{
    struct node
    {
        int val;
        node* left;
        node* right;
        node* parent;
    };
    node*root=NULL;

    void insert_value(node*p, int x)
    {
       // Your code starts here - 001
        if (root == NULL){
            p = new node();
            p->val = x;
            root = p;
        }
        if (x < p->val )
        {
            if (p->left == NULL)
            {
                node*r = new node();
                r->val = x;
                r->parent = p;
                p->left = r; 
            }
            else 
            insert_value(p->left,x);
        }
          
        if(x > p->val){
        {
            if (p->right == NULL)
            {
                node*r = new node();
                r->val = x;
                r->parent = p;
                p->right = r; 
            }
            else 
            insert_value(p->right,x);
        }
    }

       // Your code ends here - 001

    }
    
    void inorder(node*p)
    {
        // Your code starts here - 002
        if(!p) return;
        inorder(p->left);
        cout<< p->val<<endl;
        inorder(p->right);
       
       // Your code ends here - 002
    }
    
    void delete_value(int x){
        // Your code starts here - 003
        node *p = root;
        
        while (true)
        {
            if (p->val == x)
            {
                break;
            }
            if (x < p->val)
            {
                p = p->left;
            }
            else if(x > p->val){
            p = p->right;
            }
        }

        if (p->left == NULL && p->right == NULL)
        {
            if (p->parent->val > x)
            {
                p->parent->left = NULL;
            }
            else if (p->parent->val < x)
            {
                p->parent->right = NULL;
            }
            return;
        }
        
        node * r = p->left;

        while (r->right != nullptr)
        {
            r = r->right;
        }
        if (r->left != NULL)
        {
            r->parent->left = r->left;
        }
        p->val = r->val;

       
       // Your code ends here - 003

    }

};

int main() {
    
    BST* bst = new BST();
    int n, m;
    cin>>n;
    for(int i = 0;i<n;i++){
        int v;
        cin>>v;
        bst->insert_value(bst->root, v);
    } 
    cin>>m;
    for(int i = 0;i<m;i++){
        int v;
        cin>>v;
        bst->delete_value(v);
    } 
    bst->inorder(bst->root);
    return 0;
}

