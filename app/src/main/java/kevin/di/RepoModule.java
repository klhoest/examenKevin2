package kevin.di;

import dagger.Module;
import dagger.Provides;
import kevin.domain.model.repository.ItemRepository;
import kevin.presenter.MainPresenter;
import kevin.repository.ItemRepositoryStub;
import kevin.ui.StubMainView;

@Module
public class RepoModule {

    @Provides
    static ItemRepository provideRepo() {
        return new ItemRepositoryStub();
    }

    @Provides
    static MainPresenter.View provideMainPresenterView() {
        return new StubMainView();
    }
}
