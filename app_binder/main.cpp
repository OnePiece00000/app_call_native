#include <sys/types.h>
#include <binder/IPCThreadState.h>
#include <binder/ProcessState.h>
#include <binder/IServiceManager.h>
#include <cutils/log.h>
#include "GetFileInfo.h"

 
using namespace android;
 
int main(int argc, char** argv) {

    sp<ProcessState> proc(ProcessState::self());
    sp<IServiceManager> sm = defaultServiceManager();
    ALOGI("ServiceManager:%p",sm.get());
    sm->addService(String16("MyFileService"),new MyFileService());
    ProcessState::self()->startThreadPool();
    IPCThreadState::self()->joinThreadPool();

    return 0;
}
