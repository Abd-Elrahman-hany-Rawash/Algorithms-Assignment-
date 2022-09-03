#include <cstdio>
#include <vector>
#include <queue>
#include <functional>
#include <iostream>
using namespace std;
#define INF 1e9
#define MAX 100000
int A,B,T,F;
pair<int , int > pare;
vector < pair<int , int >  > M[MAX];
priority_queue < pair<int , int > , vector < pair<int , int > >,greater<pair<int ,int> > > priorityqueue;
int p[MAX];
bool flag;
void cases (int sourse)
{
    pare.first=0;
    pare.second=sourse;
    priorityqueue.push(pare);
    p[sourse]=0;
    flag = false;
    while(!priorityqueue.empty())
    {
        F=priorityqueue.top().second;
        B=priorityqueue.top().first;
        priorityqueue.pop();
        for(int i=0; i< M[F].size();i++)
        {
            A=M[F][i].first;
            B=M[F][i].second;
            if(p[A]>p[F]+B)
            {
                if(A==T)
                    flag =true;
                p[A]=p[F]+B;
                pare.first=p[A];
                pare.second=A;
                priorityqueue.push(pare);
            }

        }
    }
}
int main()
{
    int N;
    cin >> N;
    int C,D,E;
    for(int i=1; i<=N ;i++)
    {
        cin>>C>>D>>E>>T;
        for(int j=0;j<D;j++)
        {
            cin>>F>>A>>B;
            pare.first=A;
            pare.second=B;

            M[F].push_back(pare);
            pare.first=F;
            pare.second=B;

            M[A].push_back(pare);

        }
        for(int late1=0;late1<MAX;late1++)
            p[late1]=INF;
        cases( E);
        cout<<"Case # "<<i<<": ";
        if(flag)
            cout<<p[T]<<endl;
        else
            cout<<"unreachable"<<endl;
        for(int late2=0;late2<MAX;late2++)
            M[late2].clear();
    }
    return 0;
}
