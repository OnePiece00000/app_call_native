package com.hht;
import com.hht.IFileInfoCallback;

interface IGetFileInfo{
    // @utf8InCpp注解可以使生成的头文件中String与std::string对应起来
    void getFileInfo(@utf8InCpp String name);
    // register callback
    void registerCallback(IFileInfoCallback callback);

}
