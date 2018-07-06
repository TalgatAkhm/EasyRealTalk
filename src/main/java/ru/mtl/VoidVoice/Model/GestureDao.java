package ru.mtl.VoidVoice.Model;

import net.sf.autodao.Dao;
import net.sf.autodao.Finder;
import net.sf.autodao.Named;

import java.util.List;

public interface GestureDao extends Dao<Gesture, Long> {
    @Finder(query = "from Gesture select *")
    List<Gesture> getAll();

    @Finder(query="select count(*) from Gesture")
    long count();

    @Finder(query = "from Gesture where meaning = :meaning")
    Gesture getByMeaning(@Named("meaning") String meaning);
}
