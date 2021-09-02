//https://programmers.co.kr/learn/courses/30/lessons/42892?language=cpp

#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

struct Node {
    int x, y, i;
    Node* left = NULL;
    Node* right = NULL;

    Node(int x, int y, int i) {
        this->x = x;
        this->y = y;
        this->i = i;
    }

    Node() {}
};

void insertNode(Node * root, Node * next, Node * prev) {
    if (root->y < next->y) {
        if (root->x < next->x) {
            next->left = root;

            if (prev->x < next->x) prev->right = next;
            else prev->left = next;
        }
        else {
            next->right = root;

            if (prev->x < next->x) prev->right = next;
            else prev->left = next;
        }

        return;
    }

    if (root->x < next->x) {
        if (root->right == NULL) {
            root->right = next;
            return;
        }
        insertNode(root->right, next, root);
    }
    else {
        if (root->left == NULL) {
            root->left = next;
            return;
        }
        insertNode(root->left, next, root);
    }
}

void preOrder(vector<int>&ans, Node * node) {
    if (node == NULL) return;
    ans.push_back(node->i);
    preOrder(ans, node->left);
    preOrder(ans, node->right);
}

void postOrder(vector<int>&ans, Node * node) {
    if (node == NULL) return;
    postOrder(ans, node->left);
    postOrder(ans, node->right);
    ans.push_back(node->i);
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> answer;

    vector<Node> nodes;

    for (int i = 0; i < nodeinfo.size(); i++) {
        nodes.push_back(Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
    }

    sort(nodes.begin(), nodes.end(), [](Node a, Node b) {
        return a.y > b.y;
        });

    Node* garbage = new Node;
    garbage->x = -1;
    garbage->right = &nodes[0];

    for (int i = 1; i < nodeinfo.size(); i++) {
        Node* nextNode = new Node;
        
        nextNode = &nodes[i];

        insertNode(garbage->right, nextNode, garbage);
    }

    vector<int> preorder;
    preOrder(preorder, garbage->right);
    vector<int> postorder;
    postOrder(postorder, garbage->right);
    answer.push_back(preorder);
    answer.push_back(postorder);
    return answer;
}