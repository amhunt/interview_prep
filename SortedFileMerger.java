/*************************************************************************
 *  Author: Andrew Hunt
 *  Date Last Edited: 10/18/2015
 * 
 *  Compilation:  javac SortedFileMerger.java
 *  Execution:    java SortedFileMerger sorted1.txt sorted2.txt ... sortedN.txt
 *
 *  SortedFileMerger.java is a sorted file merger that prints out the items in the input 
 *  files in a sorted manner. 
 * 
 *  If the input files are unsorted, SortedFileMerger will stop
 *  and print an error message.
 *
 *************************************************************************/
import java.util.PriorityQueue;
import java.io.BufferedReader;
import java.io.FileReader;

public class SortedFileMerger {
    
    // simple class to keep track of the stream that the items come from
    private static class StreamItem implements Comparable<StreamItem> {
        public int streamIndex;
        public String value;
        
        public StreamItem(int stream, String val) {
            this.streamIndex = stream;
            this.value = val; 
        }
        
        // compares the string values
        public int compareTo(StreamItem that) {
            return this.value.compareTo(that.value);
        }
    }
    
    // merges data from in[] in sorted order and prints to stdout
    private static void mergeAndPrint (BufferedReader[] in) throws Exception {
        int numStreams = in.length;
        
        // to find next item to print efficiently
        PriorityQueue<StreamItem> pq = 
            new PriorityQueue<StreamItem>(numStreams);
        
        // insert first item from each input stream
        for (int i = 0; i < numStreams; i++) {
            String next = in[i].readLine();
            if (next != null) {
                StreamItem x = new StreamItem(i, next);
                pq.add(x);
            }
        }
        
        // print items from the priority queue and adds new ones from 
        //   respective streams
        while (!pq.isEmpty()) {
            StreamItem min = pq.poll();
            int streamIndex = min.streamIndex;
            System.out.println(min.value);
            
            // get next string from the stream whose value was last printed
            String next = in[streamIndex].readLine();
            if (next != null) {
                StreamItem hd = new StreamItem(streamIndex, next);
                
                // ensure input files are sorted
                if (min.compareTo(hd) > 0) {
                    System.out.println(
                           "Process interrupted: Input files are not sorted");
                    break;
                }
                
                pq.add(hd);
            }
        }
    }
    
    // takes filenames from stdin and initializes input streams
    public static void main(String[] args) throws Exception {
        int numStreams = args.length;
        
        // no input files provided
        if (numStreams == 0) {
            System.out.println("Please provide file names as command line args:");
            System.out.println("Ex.: SortedFileMerger " +
                                      "file1.txt file2.txt file3.txt");
            return;
        }
        
        // initialize streams and call merge method
        try {
            BufferedReader[] in = new BufferedReader[numStreams];
            for (int i = 0; i < numStreams; i++) {
                in[i] = new BufferedReader(new FileReader(args[i]));
            }
            
            mergeAndPrint(in);
            
        } catch (Exception e) {
            // if BufferedReader or FileReader throw an exception
            System.out.println("Problem occured accessing input files,");
            System.out.println("Please confirm names are correct and file is in the correct folder");
        }
    }
}