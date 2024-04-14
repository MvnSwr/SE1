package main;

public class GermanFormatNumberTransformer implements NumberTransformer{

    @Override
    public String transformNumber(int number){
        if(!(0 < number && number <= 3000)){
            return NumberTransformer.str;
        }
        if(((float)number / 1000) < 1.0){
            return Integer.toString(number);
        }else{
            return Integer.toString(number).substring(0, 1) + "." + Integer.toString(number).substring(1);
        }
    }

    @Override
    public String getTransformerType() {
        return "Transformer für deutsche Zahlenformatierungen";
    }
    
}