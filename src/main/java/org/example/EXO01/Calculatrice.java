package org.example.EXO01;


public class Calculatrice  {

    public int additionner(int a, int b) {
        result = a + b ;

        return result;
    }

    public int getState(){
        return result;
    }
    public void setResult(int result){
        this.result = result;
    }
    private int result ;


}
