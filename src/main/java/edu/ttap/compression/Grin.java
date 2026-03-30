package edu.ttap.compression;

import java.io.IOException;

/**
 * The driver for the Grin compression program.
 */
public class Grin {
    /**
     * Decodes the .grin file denoted by infile and writes the output to the
     * .grin file denoted by outfile.
     * 
     * @param infile  the file to decode
     * @param outfile the file to ouptut to
     * 
     */
    public static void decode(String infile, String outfile) throws IOException {
        BitInputStream in = new BitInputStream(infile);
        BitOutputStream out = new BitOutputStream(outfile);
        // 0x736
        int header = in.readBits(32);
        if (header != 0x736) {
            throw new IllegalArgumentException("Not a valid .grin input file.");
        } 
        //huffmann tree from the inp
        else {
            HuffmanTree tree = new HuffmanTree(in);
            tree.decode(in, out);
        }
        in.close();
        out.close();
    }

    /**
     * The entry point to the program.
     * 
     * @param args the command-line arguments.
     */
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        } else {
            decode(args[0], args[1]);   
        }
    }
}