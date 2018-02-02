import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Encoding {


	public static void main(String[] args) {
		//declarations
		
		Map<Character, Integer> freqCountMap = new HashMap<>();
		Map<Character, String> encodeMap = new HashMap<>();
		//priority queue for keeping sorted freq character data
		//using custom comparator for Huffman Node [PriorityQueueComparator]
		PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(new PriorityQueueComparator());
		Operations operations = new Operations();
		
		String input = operations.readInput();
		freqCountMap = operations.buildFrequencyCounter(freqCountMap,input);
		System.out.println("-------------");
		queue = operations.buildQueue(freqCountMap, queue);
		HuffmanNode hf = operations.buildNodeTree(queue);
		operations.buildEncodeMap(hf, "", encodeMap);
		String encodedInput = operations.encodeString(input, encodeMap);
		operations.displayMapping(encodeMap);
		System.out.println("-------------");
		System.out.println(encodedInput);
		System.out.println("-------------");
		String decodedString = operations.decodeString(encodedInput, hf);
		System.out.println(decodedString);
	}
	
}
