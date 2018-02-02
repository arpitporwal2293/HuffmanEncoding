import java.util.Comparator;

public class PriorityQueueComparator implements Comparator<HuffmanNode>{

	//custom comparator for keeping the Huffman nodes sorted
	
	@Override
	public int compare(HuffmanNode o1, HuffmanNode o2) {
		// TODO Auto-generated method stub
		if(o1.freq == o2.freq){
			return 0;
		}else return o1.freq>o2.freq?1:-1;
	}

	

	
	
}
