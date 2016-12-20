package ru.spbau.eshcherbin.homework.cw2.testClasses;

public class ClassBDependsOnA {
    public final ClassADependsOnB dependency;

    public ClassBDependsOnA(ClassADependsOnB dependency) {
        this.dependency = dependency;
    }
}

