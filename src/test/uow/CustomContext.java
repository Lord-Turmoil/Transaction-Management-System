package uow;

public class CustomContext extends DbContext {
    public DbSet<String> strings;
}
