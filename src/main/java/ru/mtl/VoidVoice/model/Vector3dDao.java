package ru.mtl.VoidVoice.model;

import net.sf.autodao.Dao;
import net.sf.autodao.Finder;

import java.util.List;

public interface Vector3dDao extends Dao<Vector3d, Long> {
    @Finder(query = "from Vector3d select *")
    List<Vector3d> getAll();

    @Finder(query="select count(*) from Vector3d")
    long count();
}
