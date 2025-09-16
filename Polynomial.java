
public class Polynomial {
    private double[] array;
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
    public void add(double inputArray[]){
        if(inputArray.length > array.length){
            for(int  i = 0; i < array.length; i++){
                inputArray[i] = inputArray[i] + array[i];
            }
            array = new double[inputArray.length];
            for(int  i = 0; i < array.length; i++){
                array[i] = inputArray[i];
            }
        }
        else{
            for(int i = 0; i < array.length; i++){
                array[i] = inputArray[i];
            }
        }
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
