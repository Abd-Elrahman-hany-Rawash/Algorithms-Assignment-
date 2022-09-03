#include <bits/stdc++.h>
using namespace std;
  
class Node
{
public:
    int key;
    Node **next;
    Node(int, int);
};
  
Node::Node(int key, int level)
{
    this->key = key;
  
    next = new Node*[level+1];
  
    memset(next, 0, sizeof(Node*)*(level+1));
};
  
class SkipList
{
    int maxlevel;
    float P;
    int level;
    Node *header;
public:
    SkipList(int, float);
    int randomLevel();
    Node* createNode(int, int);
    void insert(int);
    void deleteElement(int);
    void search(int);
    void printList();
    void printlevel(int);
};
  
SkipList::SkipList(int maxlevel, float P)
{
    this->maxlevel = maxlevel;
    this->P = P;
    level = 0;
    
    header = new Node(-1, maxlevel);
};
  
int SkipList::randomLevel()
{
    float r = (float)rand()/RAND_MAX;
    int lv = 0;
    while(r < P && lv < maxlevel)
    {
        lv++;
        r = (float)rand()/RAND_MAX;
    }
    return lv;
};
  
Node* SkipList::createNode(int key, int level)
{
    Node *n = new Node(key, level);
    return n;
};
  
void SkipList::insert(int key)
{
    Node *curr = header;
  
    Node *update[maxlevel+1];
    memset(update, 0, sizeof(Node*)*(maxlevel+1));
  
    for(int i = level; i >= 0; i--)
    {
        while(curr->next[i] != NULL &&
              curr->next[i]->key < key)
            curr = curr->next[i];
        update[i] = curr;
    }
    
    curr = curr->next[0];
  
    if (curr == NULL || curr->key != key)
    {
        // Generate a random level for node
        int rlevel = randomLevel();
  
        if(rlevel > level)
        {
            for(int i=level+1;i<rlevel+1;i++)
                update[i] = header;
  
            level = rlevel;
        }
  
        // create new node with random level generated
        Node* n = createNode(key, rlevel);
  
        for(int i=0;i<=rlevel;i++)
        {
            n->next[i] = update[i]->next[i];
            update[i]->next[i] = n;
        }
        
    }
};
  
// Delete element from skip list
void SkipList::deleteElement(int key)
{
    Node *curr = header;
  
    Node *update[maxlevel+1];
    memset(update, 0, sizeof(Node*)*(maxlevel+1));
  
    for(int i = level; i >= 0; i--)
    {
        while(curr->next[i] != NULL  &&
              curr->next[i]->key < key)
            curr = curr->next[i];
        update[i] = curr;
    }
  
    curr = curr->next[0];

    if(curr != NULL and curr->key == key)
    {
        for(int i=0;i<=level;i++)
        {
        
            if(update[i]->next[i] != curr)
                break;
  
            update[i]->next[i] = curr->next[i];
        }
  
        // Remove levels having no elements 
        while(level>0 &&
              header->next[level] == 0)
            level--;
            cout<<endl;
         cout<<"Successfully deleted key "<<key<<"\n";
    }
};
  
// Search for element in skip list
void SkipList::search(int key)
{
    Node *curr = header;
    int static m=0;
    bool flag = true;
  
    for(int i = level; i >= 0; i--)
    {
        
        while(curr->next[i] &&
               curr->next[i]->key <= key&&flag==true){
             
            m++;
            if(curr->key==key)
            {
                flag=false;
            }
            curr = curr->next[i];
        }
  
    }

    curr = curr->next[0];
  
    if(curr and curr->key == key) 
        cout<<"Found key: "<<key<<"\n";
        cout<< "number of search steps "<<m<<endl;
};
  
// Display skip list level wise
void SkipList::printList()
{
    cout<<"\n----->>Skip List<<-----"<<"\n";
    for(int i=0;i<=level;i++)
    {
        Node *node = header->next[i];
        cout<<"Level "<<i<<": ";
        while(node != NULL)
        {
            cout<<node->key<<" ";
            node = node->next[i];
        }
        cout<<"\n";
    }
    cout<<"--------------"<<endl;
};
void SkipList::printlevel(int l)
{
    
  
     for(int i=l;i<=l;i++)
    
      {
        Node *node = header->next[i];
        cout<<"Level "<<i<<": ";
        while(node != NULL)
        {
            cout<<node->key<<" ";
            node = node->next[i];
        }
        cout<<"\n";
      }
         

 
};
  
int main()
{
   
    srand((unsigned)time(0));
  
    // create SkipList object with MAXLVL and P 
    SkipList lst(10, 0.5);
  
    lst.insert(3);
    lst.insert(6);
    lst.insert(7);
    lst.insert(9);
    lst.insert(12);
    lst.insert(19);
    lst.insert(17);
    lst.insert(26);
    lst.insert(21);
    lst.insert(25);
    lst.printList();
  
    lst.search(9);
    
    lst.deleteElement(26);
    
    lst.printList();
    
    lst.printlevel(2);
    
   
}