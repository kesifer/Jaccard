/*
 * University of Central Florida
 * COP3330 - Spring 2016
 * Author: Sean Pratt
 */
package jaccard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Jaccard {
    
    List<SentenceUtils> sents;
    List<Set> shingleSets;
    
    
    public Jaccard( List<SentenceUtils> list ) {
        
        sents = list;
        generateShingleSets();
       
    }
    
    private void generateShingleSets() {
        if(shingleSets==null){
            shingleSets = new ArrayList<Set>();
        }
     for(SentenceUtils sent:sents) {
         String[] s = sent.getShingles();
         int i = s.length;
         Set<String> set = new HashSet<>(Arrays.asList(s));
         
         shingleSets.add(set);
     }
     
    }
    
    private double computeJaccard( Set<String> a, Set<String> b ) {
       int count=0;
        for(String n:a) {
            if(b.contains(n)){
                
                count++;
            }  
        }
        Set<String> set = new HashSet<>();
        set.addAll(b);
        set.addAll(a);
        double total = set.size();
        double jacval = count/total;
        
      return Math.round(jacval*10000.0)/10000.0;
    }
    
    public void showSentenceStats() {
        String s = null;
    
     
        int sentcount=0;
        for(SentenceUtils sent: sents) { 
             s = sent.getSentence();
             String[] t = sent.getShingles();
             int i = t.length;
            System.out.print("\nSentence " + sentcount + ":\n   " + s + "\n   total shingles: " + i + "\n   unique shingles: " + shingleSets.get(sentcount).size() );
            sentcount++;

        }
     
    }
    
    public void showSimilarities() {
       System.out.print("\n\nJaccard Similarity Matrix:\n");
        for(int i = 0; i<shingleSets.size(); i++ ){
            
            for(int j = 0;j<shingleSets.size(); j++){
                System.out.print(computeJaccard( shingleSets.get(i), shingleSets.get(j)) + " ");
            }
           System.out.print("\n"); 
        }
        
    }
}
