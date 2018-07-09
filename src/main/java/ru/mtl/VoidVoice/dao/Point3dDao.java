package ru.mtl.VoidVoice.dao;

import net.sf.autodao.AutoDAO;
import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import ru.mtl.VoidVoice.model.Point3d;

import java.util.List;

@AutoDAO
public interface Point3dDao extends Dao<Point3d, Long> {
    @Finder(query = "from Point3d")
    List<Point3d> getAll();

    @Finder(query="select count(*) from Point3d")
    long count();
}
