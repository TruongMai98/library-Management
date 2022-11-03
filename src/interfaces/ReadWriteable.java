package interfaces;

import java.util.List;

public interface ReadWriteable<T>{
    List<T> Read(String file);
    void save(String file, List<T> list);
}
