package ru.mtl.VoidVoice.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import ru.mtl.VoidVoice.model.Finger;

import java.util.List;

@AutoDAO
public interface FingerDao extends Dao<Finger, Long> {
    @Finder(query = "from Finger")
    List<Finger> getAll();

    @Finder(query="select count(*) from Finger")
    long count();
}
