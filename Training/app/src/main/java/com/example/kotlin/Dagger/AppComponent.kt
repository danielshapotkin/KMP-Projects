package com.example.kotlin.Dagger//package com.example.kotlin.Dagger
//
//import android.app.Application
//import android.content.Context
//import android.webkit.WebView
//import com.example.kotlin.data.Repository
//import com.example.kotlin.presentation.MainActivity
//import dagger.Component
//import dagger.Module
//import dagger.Provides
//
//
//@Component(modules = [AppModule::class])
//interface AppComponent {
//    fun inject(activity: MainActivity)
//    // Добавьте другие методы для инъекции, если нужно
//}
//
//
//@Module
//object AppModule {
//
//    @Provides
//    fun provideRepository(context: Context, webView: WebView): Repository {
//        return Repository(context, webView)
//    }
//
//    @Provides
//    fun provideContext(application: Application): Context {
//        return application.applicationContext
//    }
//
//    @Provides
//    fun provideWebView(application: Application): WebView {
//        return WebView(application)
//    }
//}
