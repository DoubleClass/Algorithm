#include <iostream>
#include<string>
#include<stack>
#define MAXVERTEX 10000   //��󶥵���
using namespace std;
int indegree[MAXVERTEX];

typedef string vertextype;    //���嶥��Ĵ洢����

typedef struct ArcNode  //�߱�ڵ�
{
	int adjvex; //�ڽӵ��򣬴洢�ö����Ӧ���±�
	struct ArcNode *next; //����ָ����һ���ڽӵ�
}ArcNode;

typedef struct VertexNode   //�����ڵ�
{
	vertextype data;    //�洢�������ݵ���Ϣ
	ArcNode *firstarc;  //�߱�ͷָ��
}AdjList[MAXVERTEX];

typedef struct
{
	AdjList adjlist;    //�����ڽӱ�
	int numvertex;  //��ǰ�ڽӱ�Ķ�����
	int numarc; //��ǰ�ڽӱ�ı���
}GraphAdjList;

//�ж�x�ǲ���G�еĽڵ�
bool ExitInGraph(GraphAdjList &G, VertexNode x) {
	for (int i = 0; i < G.numvertex; i++) {
		if (G.adjlist[i].data == x.data)
			return true;
	}
	return false;
}

//��G�нڵ�x,y��������
void Connect(GraphAdjList &G, VertexNode x, VertexNode y) {
	int xindex, yindex;
	for (int i = 0; i < G.numvertex; i++) {
		if (G.adjlist[i].data == y.data)
			yindex = i;
		if (G.adjlist[i].data == x.data)
			xindex = i;
	}
	ArcNode *e = new ArcNode;
	e->adjvex = yindex;
	e->next = G.adjlist[xindex].firstarc;
	G.adjlist[xindex].firstarc = e;
}

//Ѱ�ҵ�ǰ�ڵ���G�е�index
int FindeIndex(GraphAdjList &G, VertexNode x) {
	int xindex;
	for (int i = 0; i < G.numvertex; i++) {
		if (G.adjlist[i].data == x.data)
			return i;
	}
}

//����ͼ���ڽӱ�
void CreateAdjListGraph(GraphAdjList &G, int num)
{
	ArcNode *e;
	G.numarc = num;      //���뵱ǰͼ�ı���
	G.numvertex = 0;      //��ʼͼ�Ľڵ���Ϊ0
	for (int i = 0, j = 0; i < G.numarc; i++)    //���������,j������ʾ��ǰͷ����index
	{
		string a, b;
		cin >> a >> b;
		VertexNode x, y;
		x.data = a, y.data = b;
		if (ExitInGraph(G, x) && ExitInGraph(G, y)) {
			Connect(G, x, y);
		}
		else if (!ExitInGraph(G, x) && ExitInGraph(G, y)) {
			e = new ArcNode;
			G.adjlist[j].data = x.data;     //����ǰ�ڵ㸳ֵdata
			e->adjvex = FindeIndex(G, y);   //��e��ֵ 
			e->next = NULL;
			G.adjlist[j].firstarc = e;

			j++;
			G.numvertex++;
		}
		else if (ExitInGraph(G, x) && !ExitInGraph(G, y)) {
			e = new ArcNode;
			G.adjlist[j].data = y.data;
			G.adjlist[j].firstarc = NULL;
			e->adjvex = j;
			int xindex = FindeIndex(G, x);
			e->next = G.adjlist[xindex].firstarc;
			G.adjlist[xindex].firstarc = e;
			j++;
			G.numvertex++;
		}
		else {
			e = new ArcNode;

			G.adjlist[j].data = x.data;
			G.adjlist[++j].data = y.data;
			G.adjlist[j].firstarc = NULL;

			e->adjvex = j;
			e->next = NULL;

			G.adjlist[j - 1].firstarc = e;

			G.numvertex += 2;
			j++;
		}

	}

}

void FindInDegree(GraphAdjList &g, int indegree[])
{                                     //��ÿ����������
	int i;
	ArcNode *p;
	for (i = 0; i<g.numvertex; i++)
		indegree[i] = 0;
	for (i = 0; i<g.numvertex; i++)
	{
		p = g.adjlist[i].firstarc;
		while (p)
		{
			indegree[p->adjvex]++;
			p = p->next;
		}
	}
}
bool TopologicalSort(GraphAdjList &g)  //�ж�ͼ���Ƿ���ڻ�· ���� ���� true
{
	for (int i = 0; i<g.numvertex; i++)
		indegree[i] = 0;
	int count;
	int k, i;
	ArcNode *p;
	stack<int>s;
	FindInDegree(g, indegree);   //�Ը����������
	for (i = 0; i<g.numvertex; i++)  //�����Ϊ0�Ķ���ѹ��ջ
		if (!indegree[i])
		{
			s.push(i);
			//visited[i] = 1;
		}
	if (s.size() != 1)return true;//����ӵ���������Ϊ0 �ĵ�
	count = 0;
	while (!s.empty())
	{
		i = s.top();
		s.pop();
		cout<<g.adjlist[i].data <<" ";   //���������������
		count++;
		for (p = g.adjlist[i].firstarc; p; p = p->next)
		{
			k = p->adjvex;
			//visited[k] = 1;
			if (!(--indegree[k]))
				s.push(k);
		}
	}

	if ((count == g.numvertex))
	{
		return false;//���˵��û�л�
	}
	else
		return true;
	//printf("\n��ͼ�л�·\n");
}

int main()
{
	int n;
	while (cin >> n, n) {
		GraphAdjList G;
		CreateAdjListGraph(G, n);
		if (TopologicalSort(G))
			cout << "No" << endl;
		else
			cout << "Yes" << endl;
		n = 0;
	}
	//system("pause");
	return 0;
}