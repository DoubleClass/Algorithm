#include <iostream>
#include<string>
#include<stack>
#define MAXVERTEX 10000   //最大顶点数
using namespace std;
int indegree[MAXVERTEX];

typedef string vertextype;    //定义顶点的存储类型

typedef struct ArcNode  //边表节点
{
	int adjvex; //邻接点域，存储该顶点对应的下标
	struct ArcNode *next; //链域，指向下一个邻接点
}ArcNode;

typedef struct VertexNode   //顶点表节点
{
	vertextype data;    //存储顶点数据的信息
	ArcNode *firstarc;  //边表头指针
}AdjList[MAXVERTEX];

typedef struct
{
	AdjList adjlist;    //定义邻接表
	int numvertex;  //当前邻接表的顶点数
	int numarc; //当前邻接表的边数
}GraphAdjList;

//判断x是不是G中的节点
bool ExitInGraph(GraphAdjList &G, VertexNode x) {
	for (int i = 0; i < G.numvertex; i++) {
		if (G.adjlist[i].data == x.data)
			return true;
	}
	return false;
}

//把G中节点x,y连接起来
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

//寻找当前节点在G中的index
int FindeIndex(GraphAdjList &G, VertexNode x) {
	int xindex;
	for (int i = 0; i < G.numvertex; i++) {
		if (G.adjlist[i].data == x.data)
			return i;
	}
}

//建立图的邻接表
void CreateAdjListGraph(GraphAdjList &G, int num)
{
	ArcNode *e;
	G.numarc = num;      //输入当前图的边数
	G.numvertex = 0;      //初始图的节点数为0
	for (int i = 0, j = 0; i < G.numarc; i++)    //建立顶点表,j用来表示当前头结点的index
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
			G.adjlist[j].data = x.data;     //给当前节点赋值data
			e->adjvex = FindeIndex(G, y);   //给e赋值 
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
{                                     //求每个顶点的入度
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
bool TopologicalSort(GraphAdjList &g)  //判断图中是否存在回路 存在 返回 true
{
	for (int i = 0; i<g.numvertex; i++)
		indegree[i] = 0;
	int count;
	int k, i;
	ArcNode *p;
	stack<int>s;
	FindInDegree(g, indegree);   //对各顶点求入度
	for (i = 0; i<g.numvertex; i++)  //将入度为0的顶点压入栈
		if (!indegree[i])
		{
			s.push(i);
			//visited[i] = 1;
		}
	if (s.size() != 1)return true;//不能拥有两个入读为0 的点
	count = 0;
	while (!s.empty())
	{
		i = s.top();
		s.pop();
		cout<<g.adjlist[i].data <<" ";   //输出拓扑排序序列
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
		return false;//这就说明没有环
	}
	else
		return true;
	//printf("\n该图有回路\n");
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