#ifndef GET_FILE_INFO_H
#define GET_FILE_INFO_H

#include <com/hht/BnGetFileInfo.h>
#include <com/hht/BnFileInfoCallback.h>
#include "FileInfo.h"

class MyFileService:public com::hht::BnGetFileInfo
{
    public:
        MyFileService();
        ~MyFileService();
        ::android::sp<::com::hht::IFileInfoCallback> mycallback;
        virtual::android::binder::Status getFileInfo(const ::std::string& name);
        virtual::android::binder::Status registerCallback(const ::android::sp<::com::hht::IFileInfoCallback>& callback);
};
#endif
