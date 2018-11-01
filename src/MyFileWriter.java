import config.GrammarMain;
import config.GrammarOptions;
import config.Options;
import huffman.HuffmanAlgorithmResult;
import log.Log;

import java.io.*;
import java.util.Map;

public class MyFileWriter {
    private BufferedOutputStream writerResult;
    private BufferedOutputStream writerTree;
    private boolean treeWritten;
    private String codeMode;

    public MyFileWriter(Options options) throws IOException {
        FileOutputStream outStreamResult = new FileOutputStream(options.configMain.get(GrammarMain.OUT));
        this.writerResult = new BufferedOutputStream(outStreamResult);
        codeMode = options.configOptions.get(GrammarOptions.CODE_MODE);
        if (options.configOptions.get(GrammarOptions.HUFFMAN_TABLE) != null && codeMode.equals("0")) {
            FileOutputStream outStreamTree = new FileOutputStream(options.configOptions.get(GrammarOptions.HUFFMAN_TABLE));
            this.writerTree = new BufferedOutputStream(outStreamTree);
        }

        treeWritten = false;
    }

    public final void writeOutputFile(HuffmanAlgorithmResult result) { //writeToFile
        try {
            writerResult.write(result.getResult());//////////////
            writerResult.flush();
            if (codeMode.equals("0"))
                writeHuffmanTree(result.getHuffmanTree());
        } catch (IOException e) {
            Log.logReport("Writing output file error.");
            return;
        }
    }

    public void writeHuffmanTree(Map<Byte, String> huffmanTable) throws IOException {
        if (treeWritten)
            return;
        for (byte c : huffmanTable.keySet()) {
            writerTree.write(c);
            writerTree.write(("=" + huffmanTable.get(c) + System.lineSeparator()).getBytes());
        }
        treeWritten = true;
        writerTree.flush();
    }

    public final void close() {
        try {
            writerResult.close();
            if (codeMode.equals("0"))
                writerTree.close();
        } catch (IOException e) {

        }
    }
}
