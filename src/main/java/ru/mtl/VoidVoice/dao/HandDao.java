package ru.mtl.VoidVoice.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Finder;
import net.sf.autodao.Dao;
import org.springframework.transaction.annotation.Transactional;
import ru.mtl.VoidVoice.model.Hand;

import java.util.List;

@AutoDAO
public interface HandDao extends Dao<Hand, Long> {
    @Finder(query = "from Hand")
    List<Hand> getAll();

    @Finder(query="select count(*) from Hand")
    long count();
}
