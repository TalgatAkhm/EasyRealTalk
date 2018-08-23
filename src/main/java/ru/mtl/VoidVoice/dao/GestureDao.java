package ru.mtl.VoidVoice.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;
import org.springframework.stereotype.Component;
import ru.mtl.VoidVoice.model.Gesture;
import ru.mtl.VoidVoice.model.KeyPoint;

import java.util.List;

@AutoDAO
public interface GestureDao extends Dao<Gesture, Long> {
    @Finder(query = "from Gesture")
    List<Gesture> getAll();

    @Finder(query="select count(*) from Gesture")
    long count();

    @Finder(query = "from Gesture where meaning = :meaning")
    Gesture getByMeaning(@Named("meaning") String meaning);

    @Finder(query = "from Gesture where :keyPoint in keyPointList")
    Gesture getByKeyPoint(@Named("keyPoint") KeyPoint keyPoint);
}
