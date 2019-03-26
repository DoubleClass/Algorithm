package homework;


import java.util.Scanner;


import java.util.*;


class Array {
	public String[]s;
	int[]index;
	int size;
	public Array(){
		s=new String[10000];
		index=new int[10000];
		size=0;
	}
	void add(String x,int ii){
		s[size]=x;
		index[size]=ii;
		size++;
	}
	int size(){
		return size;
	}
	
	int FindIndex(String x){
		for(int i=0;i<size;i++){
			if(s[i].equals(x)){
				return index[i];
			}
		}
		return -1;
	}
	
}
class myMap{
	String s;
	int i;
	void set(String s,int i){
		this.s =s;
		this.i=i;
	}
	
}

class ArcNode{
	//边表节点
	int adjvex; //邻接点域，存储该顶点对应的下标
	ArcNode next; //链域，指向下一个邻接点
	
}

class VertexNode<type> //顶点表节点
{
	type data;    //存储顶点数据的信息
	ArcNode firstarc;  //边表头指针
}
public class Graph {
	final int MAX=10000;
	VertexNode<String>[] AdjList;
	int numvertex;  //当前邻接表的顶点数
	int numarc; //当前邻接表的边数
	Array node;
	
	void print(){
		System.out.println("当前节点数:"+numvertex);
		System.out.println("当前边数:"+numarc);
		System.out.println("当前图的情况");
		for(int i = 0; i < numvertex; i++)
	    {
	        ArcNode p = AdjList[i].firstarc;
	        System.out.print(AdjList[i].data+" ");
	        while(p!=null)
	        {
	        	//System.out.println("****");
	        	System.out.print(p.adjvex+" ");
	            
	            p = p.next;
	        }
	        System.out.println();
	    }
	
	}
	
	void FindInDegree(int indegree[])
	{                                     //求每个顶点的入度
		int i;
		ArcNode p;
		for (i = 0; i<numvertex; i++)
			indegree[i] = 0;
		for (i = 0; i<numvertex; i++)
		{
			p = AdjList[i].firstarc;
			while (p!=null)
			{
				indegree[p.adjvex]++;
				p = p.next;
			}
		}
	}
	
	boolean TopologicalSort()  //判断图中是否存在回路 存在 返回 true
	{
		int[] indegree = new int[MAX];
		
		for (int i = 0; i<numvertex; i++){
			indegree[i] = 0;
		}
		
		int count;
		int k, i;
		ArcNode p;
		Stack<Integer>s = new Stack<Integer>();
		FindInDegree(indegree);   //对各顶点求入度
		
		
		for (i = 0; i<numvertex; i++)  //将入度为0的顶点压入栈
			if (indegree[i]==0)
			{
				s.push(i);
				//visited[i] = 1;
			}
		if (s.size() != 1){
			System.out.println("the first if");
			return true;//不能拥有两个入读为0 的点
		}
		count = 0;
		while (!s.empty())
		{
			i=s.peek();
			s.pop();
			//cout<<g.adjlist[i].data <<" ";   //输出拓扑排序序列
			count++;
			for (p = AdjList[i].firstarc; p!=null; p = p.next)
			{
				
				k = p.adjvex;
			//	System.out.println("k:"+k);
				//visited[k] = 1;
				if ((--indegree[k])==0)
					s.push(k);
			}
		}

		if ((count == numvertex))
		{
			return false;//这就说明没有环
		}
		else{
			System.out.println("the count is:"+count+"the numvertex is:"+numvertex);
			System.out.println("the last esle");
			return true;
		}
		//printf("\n该图有回路\n");
	}

	
	
	
	
		@SuppressWarnings("unchecked")
	void CreateAdjListGraph(int n){            //构建一个有向图
		this.AdjList = new VertexNode[MAX];             //初始化邻接表
		for(int i=0;i<100;i++){
			this.AdjList[i]=new VertexNode<String>();
		}
		
		Scanner scc = new Scanner(System.in);
		String[] winner=new String[n];                  //获取每行的两个string数据
		String[] loser=new String[n];     
		
		node = new Array();           //创建一个背包
		
		for(int i = 0,j=0;i<n;i++){
			winner[i]=scc.next();
			loser[i]=scc.next();
			if((node.FindIndex(winner[i]))==-1){
				//System.out.print(node.FindIndex(winner[i]));
				node.add(winner[i],j);
				j++;
			}
			if((node.FindIndex(loser[i]))==-1){
				node.add(loser[i],j);
				j++;
			}//把节点全放到背包中去
		}
		//scc.close();
		
		/*for(int i=0;i<node.size();i++){
			System.out.print(node.s[i]+" ");
			
		}*/
		//System.out.println();
		int i=0;
		this.numvertex = node.size();
		this.numarc = n;
		
		for (int t=0;t<node.size();t++) {
			this.AdjList[t].data = node.s[t];   //输入顶点信息
	        this.AdjList[t].firstarc = null;   //将表边指针置为空
            
        }
		
		for(int k = 0; k < this.numarc; k++)
	    {
	       /* int i, j, w;
	        cin >> i >> j >> w; 输入边两边的顶点和边上的权重*/
	        ArcNode e = new ArcNode();   //创建一个表边节点指针
	        
        	
	        int indexofLoser = node.FindIndex(loser[k]);
	        int indexofWinner = node.FindIndex(winner[k]);
	        e.adjvex=indexofLoser;
	    
	        e.next = this.AdjList[indexofWinner].firstarc;
	        this.AdjList[indexofWinner].firstarc = e;
	        
	    }
	
	}
	
	
	public static void main(String[] args)throws Exception{
		int n=-1;
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		while (n!=0) {
			
			//n = Integer.parseInt(line);
			Graph G = new Graph();
			
			
			G.CreateAdjListGraph( n);
			//G.print();
			if (G.TopologicalSort())
				System.out.println("No");
			else
				System.out.println("Yes");
			sc.nextLine();
			n=sc.nextInt();		
		}
		
	sc.close();
	
	
}
}
