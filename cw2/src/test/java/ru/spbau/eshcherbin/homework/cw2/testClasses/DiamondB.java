package ru.spbau.eshcherbin.homework.cw2.testClasses;

public class DiamondB {
    public DiamondA dependency;

    public DiamondB(DiamondA dependency) {
        this.dependency = dependency;
    }
}
