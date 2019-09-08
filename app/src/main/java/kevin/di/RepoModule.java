package kevin.di;

import dagger.Module;
import dagger.Provides;
import kevin.domain.model.repository.ItemRepository;
import kevin.repository.ItemRepositoryStub;

@Module
public class RepoModule {

    @Provides
    static ItemRepository provideRepo() {
        return new ItemRepositoryStub();
    }
}
