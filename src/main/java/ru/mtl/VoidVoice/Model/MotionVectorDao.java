package ru.mtl.VoidVoice.Model;

import net.sf.autodao.Dao;
import net.sf.autodao.Finder;

import java.util.List;

public interface MotionVectorDao extends Dao<MotionVector, Long> {

    @Finder(query = "from MotionVector select *")
    List<MotionVector> getAll();

    @Finder(query="select count(*) from MotionVector")
    long count();

}
