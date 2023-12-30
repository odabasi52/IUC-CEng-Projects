#include <bits/stdc++.h>
using namespace std;

class Node {
    public:
        int data;
        Node* left;
        Node* right;
};

bool create_path(Node *root, int num, vector<Node*>& path)
{
    if (root == NULL)
        return false;

    path.push_back(root);
 
    if (root->data == num)
        return true;
 
    if ((root->left && create_path(root->left, num, path))
        || (root->right && create_path(root->right, num, path)))
        return true;
 
    //if not on right path delete path
    path.pop_back();
    return false;
}
  
Node *LCA(Node *root, int v1,int v2) {
    vector<Node*> path1, path2;
    if (!create_path(root, v1, path1) || !create_path(root, v2, path2))
        return nullptr;
    
    int i;
    for (i = 0; i < path1.size() && i < path2.size(); i++)
        if (path1[i]->data != path2[i]->data)
            break;
    return (path1[i - 1]);
}