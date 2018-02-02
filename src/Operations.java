import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Operations {

	//read input text line for encoding
	public String readInput(){
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	//get map of all character frequency
	public Map<Character, Integer> buildFrequencyCounter(Map<Character, Integer> freqCountMap,String input){
		for(Character c :input.toCharArray()){
			if(freqCountMap.containsKey(c)){
				freqCountMap.put(c, freqCountMap.get(c)+1);
			}else{
				freqCountMap.put(c, 1);
			}
		}
		return freqCountMap;
	}

	//create a Huffman class node with character and its frequency
	public HuffmanNode createNode(Character ch, Integer freq){
		return new HuffmanNode(ch,freq);
	}

	//build the priority queue which has all the Huffman Node with increasing frequency order
	public PriorityQueue<HuffmanNode> buildQueue(Map<Character, Integer> freqCountMap, PriorityQueue<HuffmanNode> queue){
		Operations operations = new Operations();
		for(Character key : freqCountMap.keySet()){
			queue.add(operations.createNode(key, freqCountMap.get(key)));
		}
		return queue;
	}

	//build a huffman tree in which nodes used frequently have less root-leaf path
	public HuffmanNode buildNodeTree(PriorityQueue<HuffmanNode> queue){
		Operations operations = new Operations();
		while(queue.size()!=1){
			HuffmanNode hf1 = queue.poll();
			HuffmanNode hf2 = queue.poll();
			HuffmanNode node = operations.createNode(null, hf1.freq+hf2.freq);
			node.left = hf1;
			node.right = hf2;
			queue.add(node);
		}

		return queue.poll();
	}

	//build the encoded map for each character used in the input
	public Map<Character, String> buildEncodeMap(HuffmanNode hf, String prefix,Map<Character,String> encodeMap){
		if(hf==null){
			return encodeMap;
		}else{
			if(hf.left==null && hf.right==null){
				encodeMap.put(hf.ch, prefix);
			}
			buildEncodeMap(hf.left, prefix+"0", encodeMap);
			buildEncodeMap(hf.right, prefix+"1", encodeMap);
		}
		return encodeMap;
	}

	//Encode the original message in Huffman Encode
	public String encodeString(String input, Map<Character,String> encodeMap){
		String encodedInput = "";
		for(Character c : input.toCharArray()){
			encodedInput += encodeMap.get(c);
		}
		return encodedInput;
	}

	//to see the mapping of each character
	public void displayMapping(Map<Character, String> encodeMap){
		for(Character c : encodeMap.keySet()){
			System.out.println(c+"->"+encodeMap.get(c));
		}
	}

	//decode the encoded string back to the original input
	public String decodeString(String encodedInput, HuffmanNode hf){
		HuffmanNode top = hf;
		String input = "";
		for(Character c : encodedInput.toCharArray()){
			if(hf.left==null && hf.right==null){
				input += hf.ch.toString();
				hf = top;
			}
			if(c.equals('0')){
				hf = hf.left;
			}else{
				hf = hf.right;
			}
		}
		return input+hf.ch.toString();
	}

}
