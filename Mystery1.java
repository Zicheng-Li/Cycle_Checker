import java.util.ArrayList;
import java.util.HashMap;

public class Mystery1 {
    ArrayList<ArrayList<String>> S;  // create a 2d array name "S"
    HashMap<String, Boolean> mark = new HashMap<String, Boolean>();  // create a HashMap to mark every visited s1

    public Mystery1() {
        S = new ArrayList<ArrayList<String>>();
    }

    public void f1(String s1, String s2) {
        if(s1==s2) {
            return;
        }
        else{
            ArrayList<String> X = new ArrayList<String>();
            X.add(s1);
            X.add(s2);
            for(ArrayList<String> i:S) {    // to prevent duplicate input
                if(i.equals(X)){
                    return;
                }
            }
            S.add(X);
        }
    }

    public int f2(String s) {
        int counter=0;
        for(ArrayList<String> i:S) {
            if(i.get(1).equals(s)) {
                counter++;
            }
        }
        return counter;
    }

    public boolean f3(String s1, String s2) {
        for(ArrayList<String> i : S) {         // to set every values in mark HashMap to false
            mark.put(i.get(0), false);
            mark.put(i.get(1), false);
        }
        if(doubleConnect(s1,s2) == true) {     // to check if any (x,y) (y,x) in S
            return true;
        }
        else {
            if(vaild(s1, s2) == true) {        // if there is (s2, s1) in S then continue search
            if(search(s1,s2)== true) {         // to search if there is a path from s1 to s2 in S
               return true;
                }
            }
       return false;
        }
    }
    
    private boolean search(String s1, String s2) {

        for(ArrayList<String> i : S) {
            if(i.get(0).equals(s1)){                       // check if any s1 in tuple equal s1 we are looking for
                if(i.get(1).equals(s2)) {                  // if any s2 in tuple equal s2 we know s1 is connected to s2, so it is true
                return true;
            }
                else{
                    if((mark.get(i.get(1))) == false) {   // if the s2 in tuple had not been visited then recur search function
                        mark.put(s1, true);
                        if(search(i.get(1), s2)) {        // if the last search return true then every recursion return true
                        return true;
                    }
                    }
                } 
            }
        }
        return false;
    }

    private boolean vaild(String s1, String s2) {
        ArrayList<String> a = new ArrayList<String>();
        a.add(s2);
        a.add(s1);
        for(ArrayList<String> i:S) {     // if there is (s2, s1) in S then return true
            if(i.equals(a)) {
                return true;
            }
        }
        return false;
    }

    private boolean doubleConnect(String s1, String s2) {
        for(int i=0; i< S.size()-1; i++) {
            for(int j=i+1; j<S.size(); j++) {
                if(S.get(i).get(0)== S.get(j).get(1)) {
                    if(S.get(i).get(1)== S.get(j).get(0)) {
                        if(S.get(i).get(0)== s1 && S.get(i).get(1)== s2){   // if any (x,y) (y,x) is equal s1,s2 then return true
                            return true;
                        }
                        else if(S.get(j).get(0)== s1 && S.get(j).get(1)== s2) {
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
            }
        }
    return false;
    }
}