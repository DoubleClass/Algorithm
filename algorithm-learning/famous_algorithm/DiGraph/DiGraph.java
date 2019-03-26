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
	//�߱�ڵ�
	int adjvex; //�ڽӵ��򣬴洢�ö����Ӧ���±�
	ArcNode next; //����ָ����һ���ڽӵ�
	
}

class VertexNode<type> //�����ڵ�
{
	type data;    //�洢�������ݵ���Ϣ
	ArcNode firstarc;  //�߱�ͷָ��
}
public class Graph {
	final int MAX=10000;
	VertexNode<String>[] AdjList;
	int numvertex;  //��ǰ�ڽӱ�Ķ�����
	int numarc; //��ǰ�ڽӱ�ı���
	Array node;
	
	void print(){
		System.out.println("��ǰ�ڵ���:"+numvertex);
		System.out.println("��ǰ����:"+numarc);
		System.out.println("��ǰͼ�����");
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
	{                                     //��ÿ����������
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
	
	boolean TopologicalSort()  //�ж�ͼ���Ƿ���ڻ�· ���� ���� true
	{
		int[] indegree = new int[MAX];
		
		for (int i = 0; i<numvertex; i++){
			indegree[i] = 0;
		}
		
		int count;
		int k, i;
		ArcNode p;
		Stack<Integer>s = new Stack<Integer>();
		FindInDegree(indegree);   //�Ը����������
		
		
		for (i = 0; i<numvertex; i++)  //�����Ϊ0�Ķ���ѹ��ջ
			if (indegree[i]==0)
			{
				s.push(i);
				//visited[i] = 1;
			}
		if (s.size() != 1){
			System.out.println("the first if");
			return true;//����ӵ���������Ϊ0 �ĵ�
		}
		count = 0;
		while (!s.empty())
		{
			i=s.peek();
			s.pop();
			//cout<<g.adjlist[i].data <<" ";   //���������������
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
			return false;//���˵��û�л�
		}
		else{
			System.out.println("the count is:"+count+"the numvertex is:"+numvertex);
			System.out.println("the last esle");
			return true;
		}
		//printf("\n��ͼ�л�·\n");
	}

	
	
	
	
		@SuppressWarnings("unchecked")
	void CreateAdjListGraph(int n){            //����һ������ͼ
		this.AdjList = new VertexNode[MAX];             //��ʼ���ڽӱ�
		for(int i=0;i<100;i++){
			this.AdjList[i]=new VertexNode<String>();
		}
		
		Scanner scc = new Scanner(System.in);
		String[] winner=new String[n];                  //��ȡÿ�е�����string����
		String[] loser=new String[n];     
		
		node = new Array();           //����һ������
		
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
			}//�ѽڵ�ȫ�ŵ�������ȥ
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
			this.AdjList[t].data = node.s[t];   //���붥����Ϣ
	        this.AdjList[t].firstarc = null;   //�����ָ����Ϊ��
            
        }
		
		for(int k = 0; k < this.numarc; k++)
	    {
	       /* int i, j, w;
	        cin >> i >> j >> w; ��������ߵĶ���ͱ��ϵ�Ȩ��*/
	        ArcNode e = new ArcNode();   //����һ����߽ڵ�ָ��
	        
        	
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
