package ru.mtl.VoidVoice.Model;

import net.sf.autodao.Finder;
import net.sf.autodao.Dao;

import java.util.List;

public interface HandDao extends Dao<Hand, Long> {
    @Finder(query = "from Hand select *")
    List<Hand> getAll();

    @Finder(query="select count(*) from Hand")
    long count();
}
