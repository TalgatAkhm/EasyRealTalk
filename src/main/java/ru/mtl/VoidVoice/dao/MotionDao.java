package ru.mtl.VoidVoice.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import org.springframework.transaction.annotation.Transactional;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.Motion;

import java.util.List;

@AutoDAO
public interface MotionDao extends Dao<Motion, Long> {
    @Finder(query = "from Motion")
    List<Motion> getAll();

    @Finder(query="select count(*) from Motion")
    long count();
}
