package ru.spbau.eshcherbin.homework.cw2.testClasses;

public class ClassADependsOnB {
    public final ClassBDependsOnA dependency;

    public ClassADependsOnB(ClassBDependsOnA dependency) {
        this.dependency = dependency;
    }
}
