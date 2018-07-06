package ru.mtl.VoidVoice.model;

import net.sf.autodao.Finder;

import java.util.List;

public interface FingerDao {
    @Finder(query = "from Finger select *")
    List<Finger> getAll();

    @Finder(query="select count(*) from Finger")
    long count();
}
