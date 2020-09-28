package org.snowfox.storage;

import org.snowfox.entity.SerialNumber;

import java.util.List;

public interface SequenceStorage {

    /**
     * 存储序列号
     * @return
     */
    public boolean storage(List<SerialNumber> numbers);

    /**
     * 获取开始序列号
     * @return
     */
    public List<SerialNumber> getStarted();
}
