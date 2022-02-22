package ext;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component("dao2")
public class DaoImpVW implements IDao {
    @Override
    public double getData() {
        System.out.println("Version Web");
        return 5;
    }
}
