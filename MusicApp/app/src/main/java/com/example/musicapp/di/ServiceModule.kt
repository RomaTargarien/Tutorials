package com.example.musicapp.di

import android.content.Context
import android.media.AudioAttributes
import com.example.musicapp.data.remote.MusicDatabase
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Singleton

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideMusicDatabase() = MusicDatabase()

    @Provides
    @ServiceScoped
    fun provideAudioAttributes() = com.google.android.exoplayer2.audio.AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    @Provides
    @ServiceScoped
    fun provideExoPlayer(@ApplicationContext context: Context, audioAttributes: com.google.android.exoplayer2.audio.AudioAttributes ) =
        SimpleExoPlayer.Builder(context).build().apply {
            setAudioAttributes(audioAttributes,true)
            setHandleAudioBecomingNoisy(true)
        }

    @Provides
    @ServiceScoped
    fun provideDataSourceFactory(@ApplicationContext context: Context) =
        DefaultDataSourceFactory(context,Util.getUserAgent(context,"Spotify App"))
}