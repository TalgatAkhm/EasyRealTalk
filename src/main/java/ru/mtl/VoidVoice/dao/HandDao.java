package ru.mtl.VoidVoice.dao;

import net.sf.autodao.Finder;
import net.sf.autodao.Dao;
import ru.mtl.VoidVoice.model.Hand;

import java.util.List;

public interface HandDao extends Dao<Hand, Long> {
    @Finder(query = "from Hand select *")
    List<Hand> getAll();

    @Finder(query="select count(*) from Hand")
    long count();
}
