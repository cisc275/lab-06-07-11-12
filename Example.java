/*
 * For this problem, do not declare anything public or private unless
 * you write a main(). You may write a main() for debugging purposes,
 * but it is not required. 
 *
 * Define a class Horse with two attributes, a string name and an
 * integer numLegs. Write a two parameter constructor and (for your convenience) 
 * a toString() method.
 *
 * Write a class named Coll3 with a static method
 * "updateName". There are three parameters to the method: a
 * collection, an old name, and a new name. For every horse in the
 * collection, if it has the same name as the parameter oldname, then
 * replace the horse's name with the parameter new name.
 *
 * example: updateName({[X,4],[G,4],[X,3]}, X, Y) changes the
 * collection to {[Y,4],[G,4],[Y,3]}.
 */
import java.util.*;
class Horse {
    String name;
    int numLegs;
    
    Horse(String n, int legs){
        name = n;
        numLegs = legs;}

    public String toString(){
		return name + ", " + numLegs;
}
    



}

class Coll3{
	public static void main(String[] args){
		ArrayList<Horse> h = new ArrayList<Horse>();
		h.add(new Horse("cool", 4));
		h.add(new Horse("bestie", 4));
		updateName(h, "cool", "fff");
		System.out.println(h.toString());
}
		public static void updateName(ArrayList<Horse> horses, String oldName, String newName){
		for(Horse h:horses){
				if(h.name == oldName){
					h.name=newName;}
}
}
}
    