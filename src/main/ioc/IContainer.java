package ioc;

public interface IContainer {
    IContainer register(Class<?> cls, Object instance);

    <T> T resolve(Class<T> cls);

    <T> T mapResolve(Class<?> cls);

    <T> T resolveRequired(Class<T> cls) throws NoSuchItemException;

    <T> T mapResolveRequired(Class<?> cls);
}
