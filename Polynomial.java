import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;



public class Polynomial {
    double[] array; //set to package private
    int[] powerArray;

    public Polynomial(){
        array = new double[1];
        powerArray = new int[1];
        array[0] = 0;
        powerArray[0] = 0;
    }    


    public Polynomial(double inputArray[], int inputPowerArray[]){
        array = Arrays.copyOf(inputArray, inputArray.length);
        powerArray = Arrays.copyOf(inputPowerArray, inputPowerArray.length);
    }


    public Polynomial(File file){//this one works 
        String line = "";
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line = reader.readLine();
        }
        catch(IOException e){
            System.err.println("An error occured when trying to read the file");
        }

        array = new double[0];
        powerArray = new int[0];

        String[] terms = line.split("[\\-\\+]");
        
        for (String term : terms) {
            array = Arrays.copyOf(array, array.length + 1);
            powerArray = Arrays.copyOf(powerArray, powerArray.length + 1);
            //check if term is only x
            if(term.length() == 1 && term.charAt(0) == 'x'){
                array[array.length - 1] = 1.0;
                powerArray[powerArray.length - 1] = 1;
            }
            //check if first term is x
            else if(term.charAt(0) == 'x'){
                array[array.length - 1] = 1.0;
                powerArray[powerArray.length - 1] = Integer.parseInt(term.substring(1));
            }

            //term is only the coefficient 
            else if (!term.contains("x")) {
                array[array.length - 1] = Double.parseDouble(term);
                powerArray[powerArray.length - 1] = 0;
            } 
        
            //check if last term is x 
            else if (term.charAt(term.length() - 1) == 'x') {
                array[array.length - 1] = Double.parseDouble(term.substring(0, term.length() - 1));
                powerArray[powerArray.length - 1] = 1;
            } 
            
            //normal term
            else {
                array[array.length - 1] = Double.parseDouble(term.substring(0, term.indexOf("x")));
                powerArray[powerArray.length - 1] = Integer.parseInt(term.substring(term.indexOf("x") + 1, term.length()));
            }
            
            //check if there exists a negative version of the term
            if(line.contains("-" + term)){
                array[array.length - 1] = array[array.length - 1] * (-1);
            }
        }
    }


    
    public void SaveToFile(String FileDestination){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FileDestination))){
            for(int i = 0; i < array.length; i++){
                if(i != 0 && array[i] > 0.0){
                    System.out.println("+ happened");
                    writer.write("+");
                }

                if(array[i] != 1.0){
                    //write the coefficient
                    writer.write(Double.toString(array[i]));
                }
                

                if(array[i] != 0){
                   //write the "x"
                    writer.write("x"); 
                }
                

                if(array[i] > 1){
                    //write the power
                    writer.write(Integer.toString(powerArray[i]));
                }
                
            }
        }
        catch(IOException e){
            System.err.println("Error(s) occured while writing to" + FileDestination);
        }
    }



    public Polynomial multiply(Polynomial p2){
        double[] Marray = new double[1];
        int[] Mpowerarray = new int[1];
        Polynomial pFinal = new Polynomial(); 

        //traverse the loop and multiply the polynomials one by one
        for(int one = 0; one < this.array.length; one++){

            //this.array is the outer shell during traversal
            for(int two = 0; two < p2.array.length; two++){

                Marray[0] = p2.array[two] * this.array[one];
                // multiply the coefficients

                Mpowerarray[0] = p2.powerArray[two] + this.powerArray[one];
                // add the powers

                Polynomial placeholder = new Polynomial(Marray, Mpowerarray);
                pFinal = pFinal.add(placeholder);
            }
        }
        //resetting the pointer to the arrays because I like those names and don't want to think of new ones.
        Marray = new double[0];
        int iMarray = 0;
        Mpowerarray = new int[0];
        int iMpowerarray = 0;

        //check for zero entries in pFinal
        for(int i = 0; i < pFinal.array.length; i++){
            if(pFinal.array[i] != 0.0){
                Marray = Arrays.copyOf(Marray, Marray.length + 1);
                Marray[iMarray] = pFinal.array[i];

                Mpowerarray = Arrays.copyOf(Mpowerarray, Mpowerarray.length + 1);
                Mpowerarray[iMpowerarray] = pFinal.powerArray[i];
                //set the new arrays to the pFinal array element that is non zero

                iMarray++;
                iMpowerarray++;
                //increase the iterators
            }
            else{
                //do nothing lol
            }
        }

        return new Polynomial(Marray, Mpowerarray);
    }



    public Polynomial add(Polynomial p2){
        double[] newArray = new double[0];
        int[] newPowerArray = new int[0];
        boolean isMatch;
        for(int one = 0; one < this.powerArray.length; one++){//go through array one int
            isMatch = false;
            for(int two = 0; two < p2.powerArray.length; two++){//go through array two int
                if(this.powerArray[one] == p2.powerArray[two]){
                    //
                    newPowerArray = Arrays.copyOf(newPowerArray, newPowerArray.length + 1);
                    newPowerArray[newPowerArray.length - 1] = this.powerArray[one];
                    //add power to the new array
                    
                    //
                    newArray = Arrays.copyOf(newArray, newArray.length + 1);
                    newArray[newArray.length - 1] = this.array[one] + p2.array[two];
                    //add coefficient to the new array

                    //
                    isMatch = true;
                    // Set the is Match to true so that afterwards things can be added
                }
                //case one if they have the same power
            }
            //
            if(!isMatch){
                newArray = Arrays.copyOf(newArray, newArray.length + 1);
                newArray[newArray.length - 1] = this.array[one];
                //Add Coefficients

                //
                newPowerArray = Arrays.copyOf(newPowerArray, newPowerArray.length + 1);
                newPowerArray[newPowerArray.length - 1] = this.powerArray[one];
                //Insert the (same) power

            }
            //Add the polynomial entries that weren't a match from this.
        }

        for(int two = 0; two < p2.powerArray.length; two++){
            isMatch = false;
            for(int one = 0; one < this.powerArray.length; one++){
                if(this.powerArray[one] == p2.powerArray[two]){
                    isMatch = true;
                    //making sure we aren't adding the duplicates again
                }
            }
            if(!isMatch){
                newArray = Arrays.copyOf(newArray, newArray.length + 1);
                newArray[newArray.length - 1] = p2.array[two];
                //insert Coefficients, this time with p2

                //
                newPowerArray = Arrays.copyOf(newPowerArray, newPowerArray.length + 1);
                newPowerArray[newPowerArray.length - 1] = p2.powerArray[two];
                //Insert the (same) power, this time with p2
            }
        }
        //look through the power array of p2 to add the unique ones

        return new Polynomial(newArray, newPowerArray);
    }


    public double[] getArray(){
        return this.array;
    }


    public int[] getPowerArray(){
        return this.powerArray;
    }


    public double evaluate(double value){
        double evalValue = 0.0;
        for(int i = 0; i < array.length; i++){
            evalValue += array[i] * Math.pow(value, powerArray[i]);
            //this time with powerArray as the power value
        }
        return evalValue;
    }


    public boolean hasRoot(double value){
        return evaluate(value) == 0.0;
    }
}
