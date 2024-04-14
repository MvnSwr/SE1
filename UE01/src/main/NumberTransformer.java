package main;

public interface NumberTransformer {
    String str = "Der Wert liegt ausserhalb des Wertebereiches!! (1-3000)";

    String transformNumber(int number);

    String getTransformerType();
}