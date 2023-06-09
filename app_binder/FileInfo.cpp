#include "FileInfo.h"

namespace com{
    namespace hht
    {
        android::status_t FileInfo::writeToParcel(android::Parcel *parcel) const{
            android::status_t res;

            if (parcel == nullptr)
            {
                return android::BAD_VALUE;
            }

            res = parcel->writeString16(name);
            if (res != android::OK) return res;
            res = parcel->writeString16(ctime);
            if (res != android::OK) return res;
            res = parcel->writeInt64(fileSize);
            if (res != android::OK) return res;
            return res;
            
        }

        android::status_t FileInfo::readFromParcel(const android::Parcel *parcel){

            if (parcel == nullptr)
            {
                return android::BAD_VALUE;
            }

            parcel->readString16();
            parcel->readString16();
            parcel->readInt64(&fileSize);

            return android::OK;
        }
        
    } // namespace hht
    
}
