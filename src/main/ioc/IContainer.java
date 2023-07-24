package ioc;

public interface IContainer {
    IContainer register(Class<?> cls, Object instance);

    <T> T resolve(Class<?> cls);

    <T> T resolveRequired(Class<?> cls) throws NoSuchItemException;
}
