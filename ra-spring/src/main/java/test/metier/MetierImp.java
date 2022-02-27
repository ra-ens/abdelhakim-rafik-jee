package test.metier;

import test.dao.IDao;

public class MetierImp implements IMetier{

    private IDao dao;

    public MetierImp() {}

    public MetierImp(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calc() {
        return dao.getData() * 5;
    }

    public IDao getDao() {
        return dao;
    }

    public void setDao(IDao dao) {
        System.out.println("Called >> " + dao);
        this.dao = dao;
    }
}
