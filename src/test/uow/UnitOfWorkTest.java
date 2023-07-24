package uow;

import ioc.Container;

public class UnitOfWorkTest {
    static class StringRepository extends Repository<String> {
        public StringRepository(CustomContext dbContext) {
            super(dbContext, "strings");
        }
    }

    public static void main(String args[]) {
        Container container = Container.getGlobal();

        // database context
        CustomContext context = new CustomContext();
        container.register(CustomContext.class, context);

        // unit of work
        UnitOfWork unitOfWork = new UnitOfWork(container);
        container.register(UnitOfWork.class, unitOfWork);

        // repository
        container.register(String.class, new StringRepository(context));

        var repo = unitOfWork.getRepository(String.class);
        System.out.println(repo);
        repo.add("Hello");
        repo.add("There");
        System.out.println(repo.findAll());

        repo = unitOfWork.getRepository(String.class);
        System.out.println(repo);
        repo.delete(x -> x.equals("There"));
        System.out.println(repo.findAll());
    }
}
