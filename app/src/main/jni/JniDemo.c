#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>

JNIEXPORT jstring JNICALL
Java_com_imooc_wangyouzhan_chatclient_Activity_NDKActivity_hello(JNIEnv *env, jobject instance) {

    // TODO


    return (*env)->NewStringUTF(env, "来之ndk");
}

JNIEXPORT jstring JNICALL
Java_com_imooc_wangyouzhan_chatclient_Activity_NDKActivity_getFromJNI(JNIEnv *env,
                                                                      jobject instance) {

    return (*env)->NewStringUTF(env, "我们的app");
}

JNIEXPORT jstring JNICALL
Java_com_imooc_wangyouzhan_chatclient_Activity_NDKActivity_sayHello(JNIEnv *env, jobject instance) {

    // TODO


    return (*env)->NewStringUTF(env, "haoma ");
}

JNIEXPORT jstring JNICALL
Java_com_imooc_wangyouzhan_chatclient_Activity_NDKActivity_updateFile(JNIEnv *env, jclass type,
                                                                      jstring file_) {
    const char *file_path = (*env)->GetStringUTFChars(env, file_, 0);

    if(file_path != NULL){
        printf("from c file_path %s", file_path);
    }

    FILE *file = fopen(file_path, "a+");
    if(file != NULL){
        __android_log_print(ANDROID_LOG_VERBOSE,"nate","file path %s", file_path);
    }

    char data[] = "I am a boy";
    int count = fwrite(data,strlen(data), 1, file);
    if(count > 0){
        __android_log_print(ANDROID_LOG_VERBOSE,"nate","form c write file success");
    }else{
        __android_log_print(ANDROID_LOG_VERBOSE,"nate","form c write file faile");
    }

    if(file != NULL){
        fclose(file);

    }



    (*env)->ReleaseStringUTFChars(env, file_, file_path);

    return (*env)->NewStringUTF(env,"成功" );
}