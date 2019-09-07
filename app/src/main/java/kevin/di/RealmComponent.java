package kevin.di;

import dagger.Component;
import kevin.datasource.ItemStorage;
import kevin.datasource.storage.RealmBuilder;

@Component(modules = StorageModule.class)
public interface RealmComponent {
    RealmBuilder getRealmBuilder();

    ItemStorage getItemStorage();
}
