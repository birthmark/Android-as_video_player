#include <jni.h>
#include <string>
#include "../cpp/common/CommonTools.h"
#define LOG_TAG "native-lib"

extern "C"{
#include "libavcodec/avcodec.h"
#include <libavformat/avformat.h>
#include "libavcodec/jni.h"
}

extern "C"
JNIEXPORT jstring

JNICALL
Java_media_ushow_as_1video_1player_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello From VideoPlayer >>>";
    return env->NewStringUTF(hello.c_str());
}

JNICALL
jint JNI_OnLoad(JavaVM* vm, void* reserved)
{
    LOGI("JNI_OnLoad");
    av_jni_set_java_vm(vm, 0);
    return JNI_VERSION_1_6;
}