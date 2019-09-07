package kevin.di;

import dagger.Module;
import dagger.Provides;
import kevin.datasource.ItemStorage;
import kevin.datasource.ItemStorageImpl;
import kevin.datasource.storage.RealmBuilder;
import kevin.datasource.storage.RealmBuilderImpl;
import kevin.datasource.storage.RealmBuilderRAM;

@Module
public class StorageModule {

    @Provides
    static ItemStorage provideItemStorage() {
        RealmComponent realmComponent = DaggerRealmComponent.create();
        RealmBuilder realmBuilder = realmComponent.getRealmBuilder();
        return new ItemStorageImpl(realmBuilder);
    }

    @Provides
    RealmBuilder provideRealmBuilder() {
        return new RealmBuilderRAM();
    }
}
