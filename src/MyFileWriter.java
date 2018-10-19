import config.GrammarMain;
import config.GrammarOptions;
import config.Options;
import huffman.HuffmanAlgorithmResult;
import log.Log;

import java.io.*;
import java.util.Map;

public class MyFileWriter {
    private BufferedWriter writerResult;
    private BufferedWriter writerTree;

    public MyFileWriter(Options options) throws IOException {
        FileOutputStream outStreamResult = new FileOutputStream(options.configMain.get(GrammarMain.OUT));
        this.writerResult = new BufferedWriter(new OutputStreamWriter(outStreamResult));
        if (options.configOptions.get(GrammarOptions.HUFFMAN_TABLE) != null) {
            FileOutputStream outStreamTree = new FileOutputStream(options.configOptions.get(GrammarOptions.HUFFMAN_TABLE));
            this.writerTree = new BufferedWriter(new OutputStreamWriter(outStreamTree));
        }
    }

    public final void writeOutputFile(HuffmanAlgorithmResult result) { //writeToFile
        try {
            writerResult.write(result.getResult());
            writerResult.flush();
            writeHuffmanTree(result.getHuffmanTree());
        } catch (IOException e) {
            Log.logReport("Writing output file error.");
            return;
        }
    }

    public void writeHuffmanTree(Map<Character, String> huffmanTable) throws IOException{
        for (char c: huffmanTable.keySet()) {
            writerTree.write(c + "=" + huffmanTable.get(c) + System.lineSeparator());
        }
        writerTree.flush();
    }

    public final void close() {
        try {
            writerResult.close();
            writerTree.close();
        } catch (IOException e) {

        }
    }
}
