package kevin.di;

import dagger.Component;
import kevin.presenter.MainPresenter;

@Component(modules = RepoModule.class)
public interface MainComponent {

    MainPresenter getMainPresenter();
}
