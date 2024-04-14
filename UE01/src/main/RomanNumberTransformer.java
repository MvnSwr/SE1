package main;

public class RomanNumberTransformer implements NumberTransformer{

    @Override
    public String transformNumber(int number){
        if(!(0 < number && number <= 3000)){
            return NumberTransformer.str;
        }
        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"}; 
        int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000}; 
        StringBuilder romanNumeral = new StringBuilder();
        for (int i = romanValues.length - 1; i >= 0; i--) { 
            while (number >= romanValues[i]) { 
                romanNumeral.append(romanSymbols[i]); 
                number -= romanValues[i]; 
            } 
        } 
        return romanNumeral.toString();
    }

    @Override
    public String getTransformerType() {
        return "Transformer für römische Zahlen";
    }
    
}