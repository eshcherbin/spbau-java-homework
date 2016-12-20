package ru.spbau.eshcherbin.homework.cw2;

import org.junit.Test;
import ru.spbau.eshcherbin.homework.cw2.testClasses.*;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class TestInjector {

    private final static String PACKAGE = "ru.spbau.eshcherbin.homework.cw2";

    @Test
    public void injectorShouldInitializeClassWithoutDependencies()
            throws Exception {
        Object object = Injector.initialize(PACKAGE + ".testClasses.ClassWithoutDependencies", Collections.emptyList());
        assertTrue(object instanceof ClassWithoutDependencies);
    }

    @Test
    public void injectorShouldInitializeClassWithOneClassDependency()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + ".testClasses.ClassWithOneClassDependency",
                Collections.singletonList(PACKAGE + ".testClasses.ClassWithoutDependencies")
        );
        assertTrue(object instanceof ClassWithOneClassDependency);
        ClassWithOneClassDependency instance = (ClassWithOneClassDependency) object;
        assertTrue(instance.dependency != null);
    }

    @Test
    public void injectorShouldInitializeClassWithOneInterfaceDependency()
            throws Exception {
        Object object = Injector.initialize(
                PACKAGE + ".testClasses.ClassWithOneInterfaceDependency",
                Collections.singletonList(PACKAGE + ".testClasses.InterfaceImpl")
        );
        assertTrue(object instanceof ClassWithOneInterfaceDependency);
        ClassWithOneInterfaceDependency instance = (ClassWithOneInterfaceDependency) object;
        assertTrue(instance.dependency instanceof InterfaceImpl);
    }

    @Test(expected = InjectionCycleException.class)
    public void injectorShouldFailOnCyclicDependencies() throws Exception {
        Injector.initialize(
                PACKAGE + ".testClasses.ClassADependsOnB",
                Arrays.asList(PACKAGE + ".testClasses.ClassADependsOnB", PACKAGE + ".testClasses.ClassBDependsOnA")
        );
    }

    @Test(expected = ImplementationNotFoundException.class)
    public void injectorShouldFailOnDependencyNotFound() throws Exception {
        Injector.initialize(
                PACKAGE + ".testClasses.ClassWithOneInterfaceDependency",
                Collections.emptyList()
        );
    }

    @Test(expected = AmbiguousImplementationException.class)
    public void injectorShouldFailOnAmbiguousDependencies() throws Exception {
        Injector.initialize(
                PACKAGE + ".testClasses.ClassWithOneInterfaceDependency",
                Arrays.asList(PACKAGE + ".testClasses.InterfaceImpl", PACKAGE + ".testClasses.AnotherInterfaceImpl")
        );
    }

    @Test
    public void injectorShouldInitializeDiamondShapedDependencies() throws Exception {
        Object object = Injector.initialize(
                PACKAGE + ".testClasses.DiamondD",
                Arrays.asList(PACKAGE + ".testClasses.DiamondA",
                        PACKAGE + ".testClasses.DiamondB",
                        PACKAGE + ".testClasses.DiamondC")
        );
        assertNotNull(object);
        assertTrue(object instanceof DiamondD);
        DiamondD instance = (DiamondD) object;
        assertNotNull(instance.dependencyB);
        assertNotNull(instance.dependencyC);
    }
}