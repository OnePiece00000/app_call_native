#ifndef FILE_INFO_H
#define FILE_INFO_H

#include <binder/Parcel.h>
#include <binder/Parcelable.h>
#include <utils/String16.h>

namespace com{
    namespace hht{
        struct FileInfo:public android::Parcelable{
            public:
                android::String16
                    name,  // file name
                    ctime; // create time
                int64_t fileSize;

            virtual android::status_t writeToParcel(android::Parcel *parcel) const override;
            virtual android::status_t readFromParcel(const android::Parcel* parcel) override;

        };
    }
}
#endif
