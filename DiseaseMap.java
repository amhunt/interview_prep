/****************************************************************************
  *  Name: Andrew Hunt
  * 
  *  Compilation: javac DiseaseMap.java
  *  Dependencies: Std Lib
  *  Constructor: DiseaseMap(int students, int infected, int rate)
  *  
  *  Description: The DiseaseMap class enables users to map the progression of
  *  a disease using a rate of contraction. 
  *  Next steps:
  *   - rates of cure
  *   - deaths
  *   - a way of testing the probability of exposure of 
  *           a single student based on their activities (ex. STIs).
  *
  ****************************************************************************/
public class DiseaseMap {
    private int numStudents;
    private int numInfected;
    private int rateOfInfection;  // % of uninfected students who will contract
                                  //                            per cycle
    
    // Create new DiseaseMap object
    public DiseaseMap(int students, int infected, int rate) {
        this.numStudents = students;
        this.numInfected = infected;
        this.rateOfInfection = rate;
    }
    
    // Get current percentageDiseased
    public double getPercDiseased() {
        return (double) this.numInfected/this.numStudents*100;
    }
    
    // Progresses the map T cycles
    public void progress(int T) {
        for (int i = 0; i < T; i++) {
            progress();
            System.out.println(this.numInfected);
        }
    }
    
    // Progresses the map one time cycle
    private void progress() {
        int newlyInfected = rateOfInfection*(numStudents-numInfected)/100;
        this.numInfected += newlyInfected;
    }
}