import java.io.File;
import java.util.Arrays;

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
Polynomial s2 = new Polynomial(new File("test2.txt"));
s = s.add(s2);
System.out.println(Arrays.toString(s.getArray()) + Arrays.toString(s.getPowerArray()));
s.SaveToFile(file.getName());
}
}

