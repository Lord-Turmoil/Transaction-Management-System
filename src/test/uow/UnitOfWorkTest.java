package uow;

import ioc.Container;

public class UnitOfWorkTest {
    static class StringRepository extends Repository<String> {
        public StringRepository(CustomContext dbContext) {
            super(dbContext, "strings");
        }
    }

    public static void main(String[] args) {
        Container container = Container.getGlobal();

        // database context
        CustomContext context = new CustomContext();
        container.addSingleton(CustomContext.class, context);

        // unit of work
        UnitOfWork unitOfWork = new UnitOfWork(container);
        container.addSingleton(UnitOfWork.class, unitOfWork);

        // repository
        container.addSingleton(String.class, new StringRepository(context));

        var repo = unitOfWork.getRepository(String.class);
        System.out.println(repo);
        repo.add("Hello");
        repo.add("There");
        System.out.println(repo.getAll());

        repo = unitOfWork.getRepository(String.class);
        System.out.println(repo);
        repo.delete(x -> x.equals("There"));
        System.out.println(repo.getAll());
    }
}
