package metier;

import dao.IDao;
import org.springframework.stereotype.Component;

@Component
public class MetierImp implements IMetier{

    private IDao dao;

    public MetierImp(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calc() {
        return dao.getData() * 5;
    }
}
