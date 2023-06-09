#include "GetFileInfo.h"
#include <sys/stat.h>
#include <sys/types.h>
#include <iostream>
#include <string>
#include <time.h>

MyFileService::MyFileService(){

}

MyFileService::~MyFileService(){

}

::android::binder::Status MyFileService::getFileInfo(const ::std::string& name){
    struct stat st;
    // 文件不存在或者是不能读取
    if (stat(name.c_str(),&st) < 0)
    {
        return ::android::binder::Status::ok();
    }
    com::hht::FileInfo fileInfo;
    fileInfo.fileSize = st.st_size;
    fileInfo.name = android::String16(name.c_str());
    fileInfo.ctime = android::String16(asctime(localtime(&(st.st_mtime))));
    mycallback->backFileInfo(fileInfo);
    return ::android::binder::Status::ok();
    
}

::android::binder::Status MyFileService::registerCallback(const ::android::sp<::com::hht::IFileInfoCallback>& callback){
    mycallback = callback;
    return ::android::binder::Status::ok();
}
