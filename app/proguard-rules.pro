# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/tushar/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/udaykumar/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-target 1.6
-optimizationpasses 5

# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep public class android.net.http.SslError
-keep public class android.webkit.WebViewClient

-dontwarn android.webkit.WebView
-dontwarn android.net.http.SslError
-dontwarn android.webkit.WebViewClient

#This will print mappings - very useful for troubleshooting.
-dump ./build/class_files.txt
-printseeds ./build/seeds.txt
-printusage ./build/unused.txt
-printmapping ./build/mapping.txt

#Some recommended settings for running with Android
-keepattributes **
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Fragment
-keep public class * extends android.support.v4.app.Fragment

# There's no way to keep all @Observes methods, so use the On*Event convention to identify event handlers
-keepclassmembers class * {
    void *(**On*Event);
}

#Need this to keep serializable members as is
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#Handling library optimization
#-repackageclasses ''
-allowaccessmodification
# The -optimizations option disables some arithmetic simplifications that Dalvik 1.0 and 1.5 can't handle.
-optimizations !code/simplification/arithmetic,!code/allocation/variable

-keep public enum * {}
-keep public interface * {}
-keepattributes **

# Keep db4o around
-keep public class com.db4o.** { *; }
-dontwarn sun.misc.Unsafe

# Keep httpmime around
-keep class org.apache.** { *; }

# Keep gson around
-keep class com.google.gson.stream.** { *; }

# Keep hockey around
-keep class net.hockeyapp.** { *; }

#Avoid 3rd party library warnings
-dontwarn net.hockeyapp.**
-dontwarn javax.xml.stream.**
-dontwarn java.awt.**,javax.security.**,java.beans.**
-dontwarn org.apache.tools.ant.**
-dontwarn com.db4o.**
-dontwarn org.simpleframework.**
-dontwarn org.junit.**
-dontwarn android.support.**
-dontwarn javax.management.**
-dontwarn java.lang.management.**
-dontwarn android.test.**
-dontwarn org.apache.commons.**
-dontwarn com.google.gson.mm.internal.UnsafeAllocator.**
-dontwarn com.google.inject.**
-dontwarn roboguice.**
-dontwarn org.mockito.**
-dontwarn com.jayway.**
-dontwarn org.objenesis.instantiator.**
-dontwarn rx.**
-dontwarn org.apache.http.*
-dontwarn com.fasterxml.jackson.**
-dontwarn org.apache.commons.logging.**
-dontwarn com.amazonaws.http.**
-dontwarn com.amazonaws.metrics.**

-keepattributes *Annotation*

-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection
-keep class com.google.gson.** { *; }
-keep class org.apache.http.** { *; }
-keep class org.apache.james.mime4j.** { *; }
-keep class com.amazonaws.services.**.*Handler

-keepnames class dagger.Lazy
-keepnames class com.mutualmobile.**
-keepnames class com.amazonaws.**

-keepnames class com.fasterxml.jackson.** { *; }
-keepnames class javax.servlet.** { *; }
-keepnames class org.ietf.jgss.** { *; }


-keep class com.google.common.** { *; }
-keep class com.firebase.** { *; }
-keep class com.shaded.fasterxml.jackson.** { *; }

-dontwarn org.w3c.dom.**
-dontwarn org.joda.time.**
-dontwarn org.shaded.apache.**
-dontwarn org.ietf.jgss.**

-dontwarn com.google.common.**

-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-dontwarn retrofit.**
-dontwarn okio.**
-dontwarn retrofit2.Platform$Java8
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.* <methods>;
}

-dontwarn okio.**
-dontwarn org.simpleframework.**
-keep class com.google.common.** { *; }


#Rxjava
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Retrolambda
-dontwarn java.lang.invoke.*

-keep class com.tramsun.shutterstock.remote.models.** { *; }

-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

-dontwarn com.squareup.okhttp.**
-dontwarn com.google.appengine.api.urlfetch.**
-dontwarn rx.**
-dontwarn retrofit.**
-keepattributes Signature
-keepattributes Annotation
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
  @retrofit.http.* <methods>;
}