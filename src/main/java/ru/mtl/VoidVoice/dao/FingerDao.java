package ru.mtl.VoidVoice.dao;

import net.sf.autodao.Finder;
import ru.mtl.VoidVoice.model.Finger;

import java.util.List;

public interface FingerDao {
    @Finder(query = "from Finger select *")
    List<Finger> getAll();

    @Finder(query="select count(*) from Finger")
    long count();
}
