package tms.service;

import uow.IUnitOfWork;

import java.util.logging.Logger;

public class BaseService implements IService {
    private IUnitOfWork unitOfWork;
    private Logger logger;

    public BaseService(IUnitOfWork unitOfWork, Logger logger) {
        this.unitOfWork = unitOfWork;
        this.logger = logger;
    }
}
