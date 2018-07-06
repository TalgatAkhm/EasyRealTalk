package ru.mtl.VoidVoice.model;

import net.sf.autodao.Dao;
import net.sf.autodao.Finder;

import java.util.List;

public interface MotionDao extends Dao<Gesture, Long> {
    @Finder(query = "from Motion select *")
    List<Motion> getAll();

    @Finder(query="select count(*) from Motion")
    long count();
}
