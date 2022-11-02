package interfaces;

import java.util.List;

public interface ReadWriteable<T> {
    List Read(String file);
    void save(String file, List list);
}
