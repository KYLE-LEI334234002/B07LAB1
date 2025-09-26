import java.io.File;

public class Driver{
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,5};
int [] c1INT = {0,3};
Polynomial p1 = new Polynomial(c1, c1INT);
double [] c2 = {-2,-9};
int [] c2INT = {1,3};
Polynomial p2 = new Polynomial(c2, c2INT);

File file = new File("test.txt"); 
Polynomial s = new Polynomial(file);
s = s.add(p2);
s.SaveToFile("test.txt");


/*
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");
*/
}
}

