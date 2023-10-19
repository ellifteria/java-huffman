import java.io.PrintStream;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class HuffmanTree {
    
    public HuffmanNode root;
    public Map<Character, String> eMap;

    public HuffmanTree(Scanner scanner) {
        root = null;
        eMap = null;
        while(scanner.hasNext())
        {
            char letter = (char)(scanner.nextInt());
            scanner.nextLine();
            String path = scanner.nextLine();
            root = add(root, letter, path, 0);
        }
        return;
    }

    public HuffmanTree(Scanner scanner, Histogram histogram)
    {
        buildHistogram(scanner, histogram);
        int[] characterCounts = histogram.getCountArray();
        PriorityQueue<HuffmanNode> characterQueue = new PriorityQueue<>();
        for(int i = 0; i < characterCounts.length; i++)
        {
            characterQueue.add(new HuffmanNode((char)(65+i), characterCounts[i]));
        }
        characterQueue.add(new HuffmanNode('\0', 1));
        while(characterQueue.size() > 1)
        {
            HuffmanNode smallest = characterQueue.poll();
            HuffmanNode secondSmallest = characterQueue.poll();
            HuffmanNode combinedNode = new HuffmanNode(smallest.getCount() + secondSmallest.getCount(), smallest, secondSmallest);
            characterQueue.add(combinedNode);

        }
        root = characterQueue.poll();
        eMap = new TreeMap<>();
    }

    private void buildHistogram(Scanner scanner, Histogram histogram)
    {
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            char[] lineArray = line.toCharArray();
            for(char letter : lineArray)
            {
                histogram.add(letter);
            }
            histogram.add('\n');
            histogram.add('\r');
        }
    }

    public HuffmanTree(Scanner scanner, MapHistogram mapHisto)
    {
        buildHistogram(scanner, mapHisto);
        Map<Character, Integer> characters = mapHisto.getMap();
        PriorityQueue<HuffmanNode> characterQueue = new PriorityQueue<>();
        for(Character character : characters.keySet())
        {
            characterQueue.add(new HuffmanNode((char)character, (int)(characters.get(character))));
        }
        characterQueue.add(new HuffmanNode((char)(characters.size()), 1));
        while(characterQueue.size() > 1)
        {
            HuffmanNode smallest = characterQueue.poll();
            HuffmanNode secondSmallest = characterQueue.poll();
            HuffmanNode combinedNode = new HuffmanNode(smallest.getCount() + secondSmallest.getCount(), smallest, secondSmallest);
            characterQueue.add(combinedNode);

        }
        root = characterQueue.poll();
        eMap = new TreeMap<>();
    }

    private void buildHistogram(Scanner scanner, MapHistogram mapHisto)
    {
        while(scanner.hasNext())
        {
            String line = scanner.nextLine();
            char[] lineArray = line.toCharArray();
            for(char letter : lineArray)
            {
                mapHisto.add(letter);
            }
            mapHisto.add('\n');
            mapHisto.add('\r');
        }
    }

    private HuffmanNode add(HuffmanNode node, Character val, String code, int spot) {
        if(spot == code.length()) 
        {
            node = new HuffmanNode(val);
        }
        else
        {
            if(node == null)
            {
                node = new HuffmanNode();
            }
            if(code.charAt(spot) == '0')
            {
                node.setLeft(add(node.getLeft(), val, code, spot + 1));
            }
            else if(code.charAt(spot) == '1')
            {
                node.setRight(add(node.getRight(), val, code, spot + 1));
            }
        }
        return node;
    }

    private void printTree(HuffmanNode node)
    {
        if(!node.isLeaf())
        {
            printTree(node.getLeft());
        }
        if(node.isLeaf())
        {
            System.out.print(" " + node.getValue());
        }
        if(!node.isLeaf())
        {
            printTree(node.getRight());
        }
        
    }

    public void printTree() {
        printTree(root);
    }

    public void decode(BitInputStream in, PrintStream out)
    {
        int running = 0;
        while(running != -1)
        {
            running = getOneChar(in);
            if(running != -1)
            {
                out.append((char)(running));
            }
        }
    }

    private int getOneChar(BitInputStream input)
    {
        int spot = 0;
        HuffmanNode node = root;
        while(!node.isLeaf())
        {
            spot = input.readBit();
            if(spot == -1)
            {
                return -1;
            }
            if(spot == 0)
            {
                node = node.getLeft();
            }
            else
            {
                node = node.getRight();
            }
        }
        int charToReturn = ((int)(node.getValue()));
        return charToReturn;
    }

    public void generateCodeFile(PrintStream out)
    {
        generateCodeFile(out, root, "");
    }
    
    private void generateCodeFile(PrintStream out, HuffmanNode node, String path)
    {
        if(!node.isLeaf())
        {
            generateCodeFile(out, node.getLeft(), path + "0");
        }
        if(node.isLeaf())
        {
            out.println("" + (int)(node.getValue()));
            out.println("" + path);
            eMap.put(node.getValue(), path);
        }
        if(!node.isLeaf())
        {
            generateCodeFile(out, node.getRight(), path +"1");
        }
    }

    public void encode(Scanner scanner, String fileName)
    {
        BitOutputStream outputFile = new BitOutputStream(fileName);
        while(scanner.hasNext())
        {
            String next = scanner.nextLine();
            for(char character : next.toCharArray())
            {
                String path = eMap.get(character);
                for(char charBit : path.toCharArray())
                {
                    int intBit = (int)charBit - 48;
                    outputFile.writeBit(intBit);
                }
            }
            String path = eMap.get('\r');
            for(char charBit : path.toCharArray())
            {
                int intBit = (int)charBit - 48;
                outputFile.writeBit(intBit);
            }
            path = eMap.get('\n');
            for(char charBit : path.toCharArray())
            {
                int intBit = (int)charBit - 48;
                outputFile.writeBit(intBit);
            }
        }
        outputFile.close();
    }
}
