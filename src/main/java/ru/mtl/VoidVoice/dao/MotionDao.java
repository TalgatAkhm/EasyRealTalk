package ru.mtl.VoidVoice.dao;

import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.Motion;

import java.util.List;

public interface MotionDao extends Dao<Gesture, Long> {
    @Finder(query = "from Motion select *")
    List<Motion> getAll();

    @Finder(query="select count(*) from Motion")
    long count();
}
