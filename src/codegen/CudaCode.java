package codegen;
/*
 * Helpful class containing all our C++ code that we output
 */
public class CudaCode
{
	/*
	 * Return the code for all the pregenned helpers
	 */
	public static String helpers()
	{
		return sort() + globals() + edge() + genMain();
	}
	public static String sort()
	{
		return 
			//bubble sort because :effort:
			"__device__ inline void _sort(Node **nodes, int length)"+
			"{ for(int i=0;i<length;i++) for(int j=0;j<length;j++)"+
			"    if(nodes[j]->id < nodes[i]->id) {"+
			"      Node* tmp = nodes[i]; nodes[i] = nodes[j]; nodes[j] = tmp; }" +
			"}\n";
	}
	public static String globals()
	{
		return "__device__ Node * graph;\n"+
			"__device__ bool *_gchanged;\n";
	}
	public static String edge()
	{
		//For now let's make edge a noop
		return "__device__ inline bool _edge(Node *a, Node *b) {return true;}\n";
	}
	public static String edgeClass()
	{
		String base =
			"class Edge {"+
			"public:"+
			"__device__ Node* src;"+
			"__device__ Node* dst;";
		//TODO: Add in attributes here
		String from_attrs = "";
		return base +from_attrs + "};\n";
	}
	public static String nodeClass()
	{
		String base =
			"class Node {"+
			"public:"+
			"__device__ int id;"+
			"__device__ void lock();"+
			"__device__ void unlock();"+
			"__device__ Edge *in_edges;"+
			"__device__ int in_edges_size;"+
			"__device__ Edge *out_edges;"+
			"__device__ int out_edges_size;";
		//TODO: this
		String from_attrs ="";
		String function_decls = "";
		return base + from_attrs+"};"+function_decls+"\n";
	}
	public static String loadGraph()
	{
		return "void load_graph(char* fname) {i}\n";
	}
	public static String genMain()
	{
		String main =
			"int main(int argc, char **argv)"+
			"{"+
			"load_graph(argv[1]);"+
			"_action_main();"+
			"return 0;" +
			"}\n";
		return main;
	}
}
