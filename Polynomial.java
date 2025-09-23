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
