package com.soft.task.di

import com.soft.task.data.repository.MainRepoImp
import com.soft.task.domain.repository.MainRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @ApplicationScope
    @Provides
    @Singleton
    fun provideApplicationScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }


}
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoriesModule {

    @Singleton
    @Binds
    abstract fun insertUserRepo(impl: MainRepoImp): MainRepo


}





@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope

