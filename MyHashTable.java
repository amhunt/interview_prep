// stupid lil hashtable 4 myself

import java.util.LinkedList;
import java.util.ArrayList;

public class MyHashTable {
    private ArrayList<LinkedList<String>> data;
    private boolean[] containsList;
    private int numLists;
    private int n;
    
    public MyHashTable(int N) {
        this.n = 0;
        this.numLists = N;
        data = new ArrayList<LinkedList<String>>(numLists);
        containsList = new boolean[numLists];
    }
    
    public void add(String s) {
        int hash = hash(s);
        System.out.println(s + "   " + hash);
        n++;
        LinkedList<String> list = null;
        if (!containsList[hash]) {
            list = new LinkedList<String>();
            data.set(hash, list);
            containsList[hash] = true;
        } else {
            list = data.get(hash);
        }
        list.add(s);
    }
    
    public boolean exists(String s) {
        int hash = hash(s);
        LinkedList<String> list = data.get(hash);
        return list.contains(s);
    }
    
    public int hash(String s) {
        int hashScore = 0;
        for (int i = 0; i < s.length(); i++) {
            hashScore += s.charAt(i)*i;
        }
        return (hashScore%numLists);
    }
    
    public void printData() {
        for (LinkedList<String> l : data) {
            for (String s : l) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        MyHashTable ht = new MyHashTable(5);
        ht.add("hell4o");
        ht.add("hi");
        ht.printData();
    }
}