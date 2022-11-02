package interfaces;

import java.util.List;

public class BookReadWriteDB<T> implements ReadWriteable{
    @Override
    public List<T> Read(String file) {
        return null;
    }

    @Override
    public void save(String file, List list) {

    }
}
