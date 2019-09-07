package kevin.di;

import dagger.Module;
import dagger.Provides;
import kevin.datasource.ItemStorage;
import kevin.datasource.ItemStorageImpl;
import kevin.datasource.storage.RealmBuilder;
import kevin.datasource.storage.RealmBuilderImpl;
import kevin.domain.model.repository.ItemRepository;
import kevin.repository.ItemRepositoryStub;

@Module
public class StorageModule {

    @Provides
    static ItemStorage provideItemStorage() {
        RealmComponent realmComponent = DaggerRealmComponent.create();
        RealmBuilder realmBuilder = realmComponent.getRealmBuilder();
        return new ItemStorageImpl(realmBuilder);
    }

    @Provides
    static RealmBuilder provideRealmBuilder() {
        return new RealmBuilderImpl();
    }
}
