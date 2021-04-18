package rs;

import java.util.HashSet;
import java.util.Set;

public class TestApplication extends javax.ws.rs.core.Application {
    HashSet<Object> singletons = new HashSet<Object>();

    public TestApplication() {
        singletons.add(new Gate());
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}