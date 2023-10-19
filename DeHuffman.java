import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class DeHuffman {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner consoleInput = new Scanner(System.in);
        System.out.print("Enter 1 to compress a file or 2 to decompress a file: ");
        String userChoice = consoleInput.nextLine();
        if(userChoice.equals("1"))
        {
            System.out.println("Enter file to compress: ");
            String uncompressedFileName = consoleInput.nextLine();
            Scanner uncompressedFile = new Scanner(new File(uncompressedFileName)); 
            System.out.println("Enter desired code file name (include extension): ");
            String codeFileName = consoleInput.nextLine();
            PrintStream codeFile = new PrintStream(new File(codeFileName));
            System.out.println("Enter desired compressed file name (include extension): ");
            String compressedFileName = consoleInput.nextLine();
            MapHistogram histo = new MapHistogram();
            HuffmanTree tree = new HuffmanTree(uncompressedFile, histo);
            tree.generateCodeFile(codeFile);
            uncompressedFile = new Scanner(new File(uncompressedFileName));
            tree.encode(uncompressedFile, compressedFileName);
            System.out.println("Finished encoding");
        }
        else
        {
            System.out.print("Enter compressed file name: ");
            String compressedFileName = consoleInput.nextLine();
            BitInputStream compressedFile = new BitInputStream(compressedFileName);
            System.out.print("Enter code file name: ");
            String codeFileName = consoleInput.nextLine();
            Scanner codeFile = new Scanner(new File(codeFileName));
            System.out.println("Enter desired decompressed file name (include extension): ");
            String decompressedFileName = consoleInput.nextLine();
            PrintStream decompressedFile = new PrintStream(new File(decompressedFileName));
            HuffmanTree tree = new HuffmanTree(codeFile);
            tree.decode(compressedFile, decompressedFile);
            System.out.println("Finished decoding");
        }
        consoleInput.close();
    }
}
