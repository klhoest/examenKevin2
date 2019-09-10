package kevin.di;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import kevin.datasource.ItemApi;
import kevin.datasource.ItemStorageImpl;
import kevin.datasource.storage.RealmBuilderImpl;
import kevin.domain.model.Item;
import kevin.domain.model.repository.ItemRepository;
import kevin.repository.ItemRepositoryImpl;

@Module
public class RepoModule {

    @Provides
    static ItemRepository provideRepo() {
        //todo do not leave it like this
        ItemApi stubNetwork = new ItemApi() {
            @NotNull
            @Override
            public Observable<List<Item>> getFullItemList() {
                List<Item> itemList = new ArrayList<>();
                itemList.add(new Item(1, 1, "ananas", "fake1"));
                itemList.add(new Item(1, 2, "abrico", "fake2"));
                return Observable.just(itemList);
            }
        };
        return new ItemRepositoryImpl(stubNetwork, new ItemStorageImpl(new RealmBuilderImpl()));
    }
}
