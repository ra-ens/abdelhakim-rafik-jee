package dao;

import org.junit.Assert;
import org.junit.Test;

public class DaoImpTest {

    @Test
    public void isNotNull() {
        DaoImp dao = new DaoImp();
        Assert.assertNotNull(dao.getData());
    }
}
