package ru.spbau.eshcherbin.homework.cw2.testClasses;

public class DiamondD {
    public DiamondB dependencyB;
    public DiamondC dependencyC;

    public DiamondD(DiamondB dependencyB, DiamondC dependencyC) {
        this.dependencyB = dependencyB;
        this.dependencyC = dependencyC;
    }
}
