package edu.ttap.compression;

import java.util.ArrayList;
import java.util.List;

/**
 * A HuffmanTree derives a space-efficient coding of a collection of byte
 * values.
 *
 * The huffman tree encodes values in the range 0--255 which would normally
 * take 8 bits. However, we also need to encode a special EOF character to
 * denote the end of a .grin file. Thus, we need 9 bits to store each
 * byte value. This is fine for file writing (modulo the need to write in
 * byte chunks to the file), but Java does not have a 9-bit data type.
 * Instead, we use the next larger primitive integral type, short, to store
 * our byte values.
 */
public class HuffmanTree {

    /**
     * Constructs a new HuffmanTree from the given file.
     * 
     * @param in the input file (as a BitInputStream)
     */
    public class HNode {
        int value;

        HNode left;
        HNode right;

        HNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        HNode(HNode left, HNode right) {
            this.value = -1;
            this.left = left;
            this.right = right;
        }
    }

    private HNode root;

    /**
     * Make
     */
    public HuffmanTree(BitInputStream in) {
        root = deserialize(in);
    }

    private HNode deserialize(BitInputStream in) {
        int bit = in.readBit();
        if (bit == 0) {
            int value = in.readBits(9);
            return new HNode(value);
        }

        else {
            HNode left = deserialize(in);
            HNode right = deserialize(in);
            return new HNode(left, right);
        }
    }

    /**
     * Decodes a stream of huffman codes from a file given as a stream of
     * bits into their uncompressed form, saving the results to the given
     * output stream. Note that the EOF character is not written to out
     * because it is not a valid 8-bit chunk (it is 9 bits).
     * 
     * @param in the file to decompress.
     * @param out the file to write the decompressed output to.
     */
    public void decode(BitInputStream in, BitOutputStream out) {
        // TODO: fill me in!
        HNode current = root;

        while (true) {
            int bit = in.readBit();

            if(bit == 0){
                current = current.left;
            } else {
                current = current.right;
            }

            if(current.left == null && current.right == null){
                if(current.value == 256){
                    break;
                }
                out.writeBits(current.value, 8); 
                current = root;
            }

        }
    }
}