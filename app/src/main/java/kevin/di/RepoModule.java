package kevin.di;

import dagger.Module;
import dagger.Provides;
import kevin.datasource.ItemServiceImpl;
import kevin.datasource.ItemStorageImpl;
import kevin.datasource.storage.RealmBuilderImpl;
import kevin.domain.model.repository.ItemRepository;
import kevin.repository.ItemRepositoryImpl;

@Module
public class RepoModule {

    @Provides
    static ItemRepository provideRepo() {
        return new ItemRepositoryImpl(new ItemServiceImpl(), new ItemStorageImpl(new RealmBuilderImpl()));
    }
}
