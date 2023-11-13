package uow;

import uow.exception.NoSuchDbSetException;

public class DbContextTest {
    public static void main(String[] args) throws NoSuchDbSetException {
        CustomContext context = new CustomContext();
        var set = context.<String>getDbSet("strings");
        System.out.println(set);
    }
}
