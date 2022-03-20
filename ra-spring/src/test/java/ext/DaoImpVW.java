package ext;

import dao.IDao;

public class DaoImpVW implements IDao {
    @Override
    public double getData() {
        System.out.println("Version Web");
        return 5;
    }
}
