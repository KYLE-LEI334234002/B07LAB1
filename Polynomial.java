
public class Polynomial {
    double[] array; //set to package private
    public Polynomial(){
        array = new double[1];
        array[0] = 0;
    }    
    public Polynomial(double inputArray[]){
        array = new double[inputArray.length];
        for(int i = 0; i < inputArray.length; i++){
            array[i] = inputArray[i];
        }
    }


    public Polynomial add(Polynomial p2){
        double[] arrayRev;
        if(this.array.length < p2.array.length){ //p2 is longer
            arrayRev = new double[p2.array.length];
            for(int i = 0; i < p2.array.length; i++){
                if(i < this.array.length){
                    arrayRev[i] = this.array[i] + p2.array[i];
                }
                else{
                    arrayRev[i] = p2.array[i];
                }
            }
        }
        else if(this.array.length > p2.array.length){// this. is longer
            arrayRev = new double[this.array.length];
            for(int i = 0; i < this.array.length; i++){
                if(i < p2.array.length){
                    arrayRev[i] = this.array[i] + p2.array[i];
                }
                else{
                    arrayRev[i] = this.array[i];
                }
            }
        }
        else{// equal length
            arrayRev = new double[this.array.length];
            for(int i = 0; i < this.array.length; i++){
                arrayRev[i] = this.array[i] + p2.array[i];
            }
        }
        return new Polynomial(arrayRev);
    }


    public double evaluate(double value){
        double evalValue = 0.0;
        for(int i = 0; i < array.length; i++){
            evalValue += array[i] * Math.pow(value, i);
        }
        return evalValue;
    }
    public boolean hasRoot(double value){
        return evaluate(value) == 0.0;
    }
}
